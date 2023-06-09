package ba.nwt.electionmanagement.services;

import ba.nwt.electionmanagement.entities.*;
import ba.nwt.electionmanagement.exception.ErrorDetails;
import ba.nwt.electionmanagement.grpc.GrpcClient;
import ba.nwt.electionmanagement.repositories.CandidateRepository;
import ba.nwt.electionmanagement.repositories.ElectionRepository;
import ba.nwt.electionmanagement.repositories.ListaRepository;
import ba.nwt.electionmanagement.repositories.PollingStationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.Http;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ElectionService {

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PollingStationRepository pollingStationRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static GrpcClient grpcClient;

    public ElectionService() {
        grpcClient = GrpcClient.get();
    }

    private ResponseEntity<String> checkElectionExists(String name) {
        Optional<Election> optionalElection = electionRepository.getElectionByName(name);
        if (optionalElection.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"electionId","ELection ID not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        return null;
    }


    public List<PollingStation> deserializePollingStations(String pollingStationsJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<PollingStation> pollingStations = null;
        try {
            pollingStations = objectMapper.readValue(pollingStationsJson, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return pollingStations;
    }


    public String getElections() {
        List<Election> elections = electionRepository.findAll();
        String json;
        try {
            StringBuilder sb = new StringBuilder("[");
            for (Election election : elections) {
                sb.append(election.toString()).append(",");
            }
            if (elections.size() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("]");
            json = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error serializing elections to JSON: " + e.getMessage());
            return null;
        }
        System.out.println("Serialized JSON: " + json);
        return json;
    }

    public ResponseEntity createElection(Election election, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        redirectAttributes.addAttribute("electionId", election.getId());
        List<PollingStation> pollingStations = new ArrayList<>();
        for (PollingStation pollingStation: election.getPollingStations()) {
            Integer id = pollingStation.getId();
            Optional<PollingStation> optionalPollingStation = pollingStationRepository.findById(id);
            if (optionalPollingStation.isPresent()) {
                pollingStations.add(optionalPollingStation.get());
            }
            else {
                ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"pollingStationId","Polling Station ID not found");
                grpcClient.log(userId.getBody(), "Election","Create","Fail");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
            }
        }
        for (PollingStation pollingStation: pollingStations) {
            pollingStation.getElections().add(election);
            pollingStationRepository.save(pollingStation);
        }
        electionRepository.save(election);
        grpcClient.log(userId.getBody(), "Election","Create","Success");
        Map<String, Object> response = new HashMap<>();
        response.put("id", election.getId());
        response.put("name", election.getName());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<String> addLists(String name, List<Lista> liste, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        Optional<Election> optionalElection = electionRepository.getElectionByName(name);
        if (optionalElection.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"electionId","ELection ID not found");
            grpcClient.log(userId.getBody(), "Election","AddLists","Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        Election election = optionalElection.get();
        for (Lista lista : liste) {
            lista.setElection(election);
            listaRepository.save(lista);
        }
        grpcClient.log(userId.getBody(), "Election","AddLists","Success");
        return ResponseEntity.ok("Lists added successfully to election " + election.getId());
    }

    public ResponseEntity<String> getListsForElections(String name, HttpServletRequest request) {
        name = URLDecoder.decode(name,StandardCharsets.UTF_8);
        ResponseEntity<Integer> userId = getUserId(request);
        Optional<Election> optionalElection = electionRepository.getElectionByName(name);
        if (optionalElection.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"name","Election namenot found");
            grpcClient.log(userId.getBody(), "Election","getListsforElections", "Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        List<Lista> lists = listaRepository.getListasByElectionName(name);
        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(lists);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            grpcClient.log(userId.getBody(), "Election","getListsForElections","Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error while parsing to string");
        }
        return ResponseEntity.ok(json);
    }

    public ResponseEntity<String> addCandidates(String name, List<Candidate> candidates, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        Optional<Election> optionalElection = electionRepository.getElectionByName(name);
        if (optionalElection.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"electionId","ELection ID not found");
            grpcClient.log(userId.getBody(), "Election","addCandidates","Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        Election election = optionalElection.get();
        for (Candidate candidate: candidates) {
            candidate.setElection(election);
            candidateRepository.save(candidate);
            election.addCandidate(candidate);
        }
        electionRepository.save(election);
        grpcClient.log(userId.getBody(), "Election","addCandidates","Success");
        return ResponseEntity.ok("Candidates added successfully to election " + election.getId());
    }

    public ResponseEntity<String> getCandidates(String name, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        Optional<Election> optionalElection = electionRepository.getElectionByName(name);
        if (optionalElection.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"electionId","ELection ID not found");
            grpcClient.log(userId.getBody(), "Election","getCandidates","Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }

        List<Candidate> candidates = candidateRepository.getCandidatesByElectionName(name);
        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(candidates);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            grpcClient.log(userId.getBody(), "Election","getCandidates","Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error while parsing to string");
        }
        grpcClient.log(userId.getBody(), "Election","getCandidates","Success");
        return ResponseEntity.ok(json);
    }

    public ResponseEntity<String> getPollingStations(String name, HttpServletRequest request) {
        ResponseEntity<String> responseEntity = checkElectionExists(name);
        ResponseEntity<Integer> userId = getUserId(request);
        if (responseEntity != null) {
            grpcClient.log(userId.getBody(), "Election","getPollingStations","Fail");
            return responseEntity;
        }
        String userManagementUrl = "http://auth-service/pollingStations";
        ResponseEntity response = communicate(request,userManagementUrl);
        grpcClient.log(userId.getBody(), "Election","getPollingStations","Success");
        return response;
    }

    public ResponseEntity addElectionToPollingStations(String name, List<Long> pollingStationIds, HttpServletRequest request) {
        Optional<Election> optionalElection = electionRepository.getElectionByName(name);
        ResponseEntity<Integer> userId = getUserId(request);
        if (optionalElection.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"electionId","Election ID not found");
            grpcClient.log(userId.getBody(), "Election","addElectionsToPollingStations","Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        Election election = optionalElection.get();
        String userManagementUrl = "http://auth-service/pollingStations";
        ResponseEntity<String> response = communicate(request,userManagementUrl);
        List<PollingStation> pollingStations = deserializePollingStations(response.getBody());
        List<PollingStation> filteredPollingStations = new ArrayList<>();
        for (PollingStation pollingStation : pollingStations) {
            if (pollingStationIds.contains(pollingStation.getId().longValue())) {
                pollingStation.setId(null);
                filteredPollingStations.add(pollingStation);
            }
        }
        if (!filteredPollingStations.isEmpty()) {
            for (PollingStation pollingStation: filteredPollingStations) {
                election.addPollingStation(pollingStation);
                pollingStation.addElections(election);
            }
            electionRepository.save(election);
            for (PollingStation pollingStation: filteredPollingStations) {
                Optional<PollingStation> optionalPollingStation = pollingStationRepository.findByName(pollingStation.getName());
                if (optionalPollingStation.isEmpty()) {
                    pollingStationRepository.save(pollingStation);
                }
                else {
                    PollingStation pollingStation1 = optionalPollingStation.get();
                    pollingStation1.addElections(election);
                    pollingStationRepository.save(pollingStation1);
                }
            }
        }
        grpcClient.log(userId.getBody(), "Election","addElectionsToPollingStations","Success");
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("id", election.getId());
        responseData.put("name", election.getName());
        return ResponseEntity.ok(responseData);
    }

    public ResponseEntity<Integer> getUserId(HttpServletRequest request) {
        HttpEntity<String> entity = getStringHttpEntity(request, "body");

        return restTemplate.exchange("http://auth-service/users/id", HttpMethod.GET, entity, Integer.class);
    }

    private static HttpEntity<String> getStringHttpEntity(HttpServletRequest request, String body) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // Skip past "Bearer "
        }

        HttpHeaders headers = new HttpHeaders();
        assert token != null;
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        return entity;
    }

    public ResponseEntity communicate(HttpServletRequest request, String userManagementUrl) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // Skip past "Bearer "
        }
        assert token != null;

        // Prepare HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        // Prepare HTTP entity
        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        return restTemplate.exchange(userManagementUrl, HttpMethod.GET, entity, String.class);
    }

    public PollingStation getPollingStationForUser(HttpServletRequest request) {
        HttpEntity<String> entity = getStringHttpEntity(request, null);
        return restTemplate.exchange("http://auth-service/pollingStations/user", HttpMethod.GET, entity, PollingStation.class).getBody();
    }



    public String getElectionsForUser(HttpServletRequest request) {
        PollingStation pollingStation = getPollingStationForUser(request);
        List<Election> elections = electionRepository.findElectionsByPollingStationName(pollingStation.getName());
        HttpEntity<String> entity = getStringHttpEntity(request, "body");

        List<Election> electionsToRemove = new ArrayList<>();

        for (Election election: elections) {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://voting-service/voting/get-vote-by-election")
                    .queryParam("electionId", Integer.valueOf(election.getId().intValue()))
                    .queryParam("voterId",getUserId(request).getBody());

            ResponseEntity<String> povrat = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

            ObjectMapper mapper = new ObjectMapper();
            Boolean hasVote = null;

            try {
                VoteStatus voteStatus = mapper.readValue(povrat.getBody(), VoteStatus.class);
                if (voteStatus != null) {
                    hasVote = voteStatus.getHasVote();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (hasVote != null && hasVote.booleanValue()) {
                electionsToRemove.add(election);
            }
        }

        elections.removeAll(electionsToRemove);

        List<String> electionStrings = elections.stream().map(Election::toString).collect(Collectors.toList());
        String jsonArray = String.join(", ", electionStrings);
        jsonArray = "[" + jsonArray + "]";

        return jsonArray;
    }


    public ResponseEntity getElectionIdByName(String electionName, HttpServletRequest request) {
        electionName = URLDecoder.decode(electionName,StandardCharsets.UTF_8);
        Optional<Election> optionalElection = electionRepository.getElectionByName(electionName);
        if (optionalElection.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"name","No election by that name");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        /*if (!optionalElection.get().getStatus().equals("Finished")) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"status","Election is not finished");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }*/
        return ResponseEntity.status(HttpStatus.OK).body(optionalElection.get().getId());
    }

    public ResponseEntity getCandidateIdByName(String firstName, String lastName, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        firstName = URLDecoder.decode(firstName,StandardCharsets.UTF_8);
        lastName = URLDecoder.decode(lastName,StandardCharsets.UTF_8);
        Optional<Candidate> optionalCandidate = candidateRepository.getCandidateByFirstNameAndLastName(firstName,lastName);
        if (optionalCandidate.isEmpty()) {
            grpcClient.log(userId.getBody(), "Election","Get candidate id by name","Fail");
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"name","No candidate by that name");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        grpcClient.log(userId.getBody(), "Election","Get candidate id by name","Success");
        return ResponseEntity.status(HttpStatus.OK).body(optionalCandidate.get().getId());
    }

    public ResponseEntity getListIdByName(String name, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        name = URLDecoder.decode(name,StandardCharsets.UTF_8);
        Optional<Lista> optionalLista = listaRepository.getListaByName(name);
        if (optionalLista.isEmpty()) {
            grpcClient.log(userId.getBody(), "Election","Get list id by name","Fail");
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"name","No list by that name");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        grpcClient.log(userId.getBody(), "Election","Get list id by name","Success");
        return ResponseEntity.status(HttpStatus.OK).body(optionalLista.get().getId());

    }

    public ResponseEntity getCandidateNameById(Integer candidateId, HttpServletRequest request) {
        Optional<Candidate> optionalCandidate = candidateRepository.findById(candidateId);
        Integer userId = getUserId(request).getBody();
        if (optionalCandidate.isEmpty()) {
            grpcClient.log(userId, "Election","Get candidate by id","Fail");
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"id","No candidate by that id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalCandidate.get());
    }

    public ResponseEntity getListById(Integer listId, HttpServletRequest request) {
        Optional<Lista> optionalLista = listaRepository.findById(listId);
        Integer userId = getUserId(request).getBody();
        if (optionalLista.isEmpty()) {
            grpcClient.log(userId, "Election","Get list id by id","Fail");
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"id","No list by that id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalLista.get());
    }

    public ResponseEntity getCandidateByName(String candidateFirstName, String candidateLastName, HttpServletRequest request) {
        Optional<Candidate> optionalCandidate = candidateRepository.getCandidateByFirstNameAndLastName(candidateFirstName,candidateLastName);
        Integer userId = getUserId(request).getBody();
        if (optionalCandidate.isEmpty()) {
            grpcClient.log(userId, "Election", "Get candidate by name", "Fail");
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "name", "No candidate by that name");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        grpcClient.log(userId,"Election","Get candidate by name","Success");
        return ResponseEntity.ok(optionalCandidate.get());
    }

    public ResponseEntity getListByName(String listaName, HttpServletRequest request) {
        listaName = URLDecoder.decode(listaName,StandardCharsets.UTF_8);
        Optional<Lista> optionalLista = listaRepository.getListaByName(listaName);
        Integer userId = getUserId(request).getBody();
        if (optionalLista.isEmpty()) {
            grpcClient.log(userId, "Election", "Get list by name", "Fail");
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "name", "No list by that name");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        grpcClient.log(userId,"Election","Get list by name","Success");
        return ResponseEntity.ok(optionalLista.get());
    }

    public ResponseEntity getElectionById(Integer electionId, HttpServletRequest request) {
        Optional<Election> optionalElection = electionRepository.findById(electionId);
        Integer userId = getUserId(request).getBody();
        if (optionalElection.isEmpty()) {
            grpcClient.log(userId, "Election", "Get election by ID", "Fail");
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "ID", "No election by that ID");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        grpcClient.log(userId, "Election", "Get election by ID", "Fail");
        return ResponseEntity.ok(optionalElection.get());
    }
}
