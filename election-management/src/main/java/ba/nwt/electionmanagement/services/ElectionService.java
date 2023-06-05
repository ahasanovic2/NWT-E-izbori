package ba.nwt.electionmanagement.services;

import ba.nwt.electionmanagement.entities.Candidate;
import ba.nwt.electionmanagement.entities.Election;
import ba.nwt.electionmanagement.entities.Lista;
import ba.nwt.electionmanagement.entities.PollingStation;
import ba.nwt.electionmanagement.exception.ErrorDetails;
import ba.nwt.electionmanagement.grpc.GrpcClient;
import ba.nwt.electionmanagement.repositories.CandidateRepository;
import ba.nwt.electionmanagement.repositories.ElectionRepository;
import ba.nwt.electionmanagement.repositories.ListaRepository;
import ba.nwt.electionmanagement.repositories.PollingStationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    private GrpcClient grpcClient;

    public ElectionService() {
        grpcClient = GrpcClient.get();
    }

    private ResponseEntity<String> checkElectionExists(Long electionId) {
        Optional<Election> optionalElection = electionRepository.findById(electionId);
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

    public ResponseEntity<String> addLists(Long electionId, List<Lista> liste, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        Optional<Election> optionalElection = electionRepository.findById(electionId);
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

    public ResponseEntity<String> getListsForElections(Long electionId, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        Optional<Election> optionalElection = electionRepository.findById(electionId);
        if (optionalElection.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"electionId","ELection ID not found");
            grpcClient.log(userId.getBody(), "Election","getListsforElections", "Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        List<Lista> lists = listaRepository.findAllByElectionId(electionId);
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

    public ResponseEntity<String> addCandidates(Long electionId, Long listId, List<Candidate> candidates, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        Optional<Election> optionalElection = electionRepository.findById(electionId);
        if (optionalElection.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"electionId","ELection ID not found");
            grpcClient.log(userId.getBody(), "Election","addCandidates","Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        Optional<Lista> optionalLista = listaRepository.findById(listId);
        if (optionalLista.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"listId","List ID not found");
            grpcClient.log(userId.getBody(), "Election","addCandidates","Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        Lista list = optionalLista.get();
        for (Candidate candidate: candidates) {
            candidate.setLista(list);
            candidateRepository.save(candidate);
        }
        grpcClient.log(userId.getBody(), "Election","addCandidates","Success");
        return ResponseEntity.ok("Candidates added successfully to list " + list.getId());
    }

    public ResponseEntity<String> getCandidates(Long electionId, Long listId, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        Optional<Election> optionalElection = electionRepository.findById(electionId);
        if (optionalElection.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"electionId","ELection ID not found");
            grpcClient.log(userId.getBody(), "Election","getCandidates","Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }

        Optional<Lista> optionalLista = listaRepository.findById(listId);
        if (optionalLista.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"listId","List ID not found");
            grpcClient.log(userId.getBody(), "Election","getCandidates","Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }

        List<Candidate> candidates = candidateRepository.findAllByListaId(listId);
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

    public ResponseEntity<String> getPollingStations(Long electionId, HttpServletRequest request) {
        ResponseEntity<String> responseEntity = checkElectionExists(electionId);
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

    public ResponseEntity addElectionToPollingStations(Long electionId, List<Long> pollingStationIds, HttpServletRequest request) {
        Optional<Election> optionalElection = electionRepository.findById(electionId);
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
            pollingStationRepository.saveAll(filteredPollingStations);
        }
        grpcClient.log(userId.getBody(), "Election","addElectionsToPollingStations","Success");
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("id", election.getId());
        responseData.put("name", election.getName());
        return ResponseEntity.ok(responseData);
    }

    public ResponseEntity<Integer> getUserId(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // Skip past "Bearer "
        }

        HttpHeaders headers = new HttpHeaders();
        assert token != null;
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        return restTemplate.exchange("http://auth-service/users/id", HttpMethod.GET, entity, Integer.class);
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
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // Skip past "Bearer "
        }

        HttpHeaders headers = new HttpHeaders();
        assert token != null;
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        return restTemplate.exchange("http://auth-service/pollingStations/user", HttpMethod.GET, entity, PollingStation.class).getBody();
    }



    public String getElectionsForUser(HttpServletRequest request) {
        PollingStation pollingStation = getPollingStationForUser(request);
        List<Election> elections = electionRepository.findElectionsByPollingStationName(pollingStation.getName());
        List<String> electionStrings = elections.stream().map(Election::toString).collect(Collectors.toList());
        String jsonArray = String.join(", ", electionStrings);
        jsonArray = "[" + jsonArray + "]";

        return jsonArray;
    }

    public ResponseEntity getListsForElectionByName(String name, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        System.out.println("Name of election is " + name);
        name = URLDecoder.decode(name, StandardCharsets.UTF_8);
        Optional<Election> optionalElection = electionRepository.getElectionByName(name);
        if (optionalElection.isEmpty()) {
            grpcClient.log(userId.getBody(), "Election","Get lists for election by name","Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDetails(LocalDateTime.now(),"name","No election found by that name"));
        }
        Election election = optionalElection.get();
        List<Lista> lists = election.getList();
        List<String> listStrings = lists.stream().map(Lista::toString).collect(Collectors.toList());
        String jsonArray = String.join(", ", listStrings);
        jsonArray = "[" + jsonArray + "]";

        grpcClient.log(userId.getBody(), "Election","Get lists for election by name","Success");
        return ResponseEntity.status(HttpStatus.OK).body(jsonArray);
    }

    public ResponseEntity getCandidatesForList(String name, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        name = URLDecoder.decode(name, StandardCharsets.UTF_8);
        Optional<Lista> optionalLista = listaRepository.getListaByName(name);
        if (optionalLista.isEmpty()) {
            grpcClient.log(userId.getBody(), "Election","Get candidates by lista name","Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDetails(LocalDateTime.now(),"name","No lista found by that name"));
        }
        Lista lista = optionalLista.get();
        List<Candidate> candidates = lista.getCandidates();
        List<String> listStrings = candidates.stream().map(Candidate::toString).collect(Collectors.toList());
        String jsonArray = String.join(", ", listStrings);
        jsonArray = "[" + jsonArray + "]";

        grpcClient.log(userId.getBody(), "Election","Get candidates by lista name","Success");
        return ResponseEntity.status(HttpStatus.OK).body(jsonArray);
    }
}
