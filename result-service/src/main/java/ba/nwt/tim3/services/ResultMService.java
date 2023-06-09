package ba.nwt.tim3.services;


import ba.nwt.tim3.exception.ErrorDetails;
import ba.nwt.tim3.grpc.GrpcClient;
import ba.nwt.tim3.interfaces.*;
import ba.nwt.tim3.models.*;
import com.ctc.wstx.shaded.msv_core.util.Uri;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.Http;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultMService {


/*
    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    PollingStationRepository pollingStationRepository;

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    ListRepository listRepository;

    @Autowired
    private RestTemplate restTemplate;

    private GrpcClient grpcClient;

    public ResultMService() {
        grpcClient = GrpcClient.get();
    }

    public ResponseEntity<String> getResults(HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);

        List<Result> results = resultRepository.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            StringBuilder sb = new StringBuilder("[");
            for (Result result: results) {
                sb.append(result.toString()).append(",");
            }
            if (results.size() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("]");
            json = sb.toString();
            grpcClient.log(userId.getBody(),"Result Service", "get results", "Success");
            return ResponseEntity.ok(json);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error serializing elections to JSON: " + e.getMessage());
            grpcClient.log(userId.getBody(),"Result Service", "get results", "Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }


    }
    public ResponseEntity<String> getCandidateResultsByPollingStation(Long election_id, Long pollingStationId, Long candidateId, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        Optional<Result> optionalResult = resultRepository.findByPollingStationIdAndElectionIdAndCandidateId(election_id, pollingStationId, candidateId);
        if (optionalResult.isPresent()) {
            Result result = optionalResult.get();
            Map<String, Object> transformed = new LinkedHashMap<>();
            transformed.put("candidateId:", result.getCandidate().getId());
            transformed.put("Candidate name:", result.getCandidate().getFirstName());
            transformed.put("Result:", result.getVoteCount());

            ObjectMapper objectMapper = new ObjectMapper();
            String json = null;
            try {
                json = objectMapper.writeValueAsString(transformed);
                grpcClient.log(userId.getBody(),"Result Service", "get candidate result by polling station", "Success");
                return ResponseEntity.ok(json);
            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
                grpcClient.log(userId.getBody(),"Result Service", "get candidate result by polling station", "Fail");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
            }
        }
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "election_id, pollingStationId, candidateId", "Election id, polling station id or candidate id not found");
        grpcClient.log(userId.getBody(),"Result Service", "get candidate result by polling station", "Fail");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
    }


    public ResponseEntity<String> getCandidateResultsByElection(Long election_id, Long candidateId, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        Optional<Result> optionalResult = resultRepository.findByElectionIdAndCandidateId(election_id, candidateId);
        if (optionalResult.isPresent()) {
            Result result = optionalResult.get();
            Map<String, Object> transformed = new LinkedHashMap<>();
            transformed.put("candidateId:", result.getCandidate().getId());
            transformed.put("Candidate name:", result.getCandidate().getFirstName());
            transformed.put("Result:", result.getVoteCount());

            ObjectMapper objectMapper = new ObjectMapper();
            String json = null;
            try {
                json = objectMapper.writeValueAsString(transformed);
                grpcClient.log(userId.getBody(),"Result Service", "get candidate result by election", "Success");
                return ResponseEntity.ok(json);
            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
                grpcClient.log(userId.getBody(),"Result Service", "get candidate result by election", "Fail");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
            }
        }
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "election_id, candidateId", "Election id or candidate id not found");
        grpcClient.log(userId.getBody(),"Result Service", "get candidate result by election", "Fail");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
    }


    public ResponseEntity<String> getListResultsByPollingStation(Long election_id, Long pollingStationId, Long listId, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        Optional<Result> optionalResult = resultRepository.findByPollingStationIdAndElectionIdAndListId(election_id, pollingStationId, listId);
        if (optionalResult.isPresent()) {
            Result result = optionalResult.get();
            Map<String, Object> transformed = new LinkedHashMap<>();
            transformed.put("ListId:", result.getList().getId());
            transformed.put("List name:", result.getList().getName());
            transformed.put("Result:", result.getVoteCount());

            ObjectMapper objectMapper = new ObjectMapper();
            String json = null;
            try {
                json = objectMapper.writeValueAsString(transformed);
                grpcClient.log(userId.getBody(),"Result Service", "get list results by polling station", "Success");
                return ResponseEntity.ok(json);
            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
                grpcClient.log(userId.getBody(),"Result Service", "get list results by polling station", "Fail");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
            }
        }
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "election_id, pollingStationId, listId", "Election id, polling station id or list id not found");
        grpcClient.log(userId.getBody(),"Result Service", "get list results by polling station", "Fail");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
    }


    public ResponseEntity<String> getListResultsByElection(Long election_id, Long listId, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        Optional<Result> optionalResult = resultRepository.findByElectionIdAndListId(election_id, listId);
        if (optionalResult.isPresent()) {
            Result result = optionalResult.get();
            Map<String, Object> transformed = new LinkedHashMap<>();
            transformed.put("ListId:", result.getList().getId());
            transformed.put("Election id:", result.getElection().getId());
            transformed.put("List name:", result.getList().getName());
            transformed.put("Result:", result.getVoteCount());

            ObjectMapper objectMapper = new ObjectMapper();
            String json = null;
            try {
                json = objectMapper.writeValueAsString(transformed);
                grpcClient.log(userId.getBody(),"Result Service", "get list result by election", "Success");
                return ResponseEntity.ok(json);
            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
                grpcClient.log(userId.getBody(),"Result Service", "get list result by election", "Fail");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
            }
        }
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "election_id, listId", "Election id or list id not found");
        grpcClient.log(userId.getBody(),"Result Service", "get list result by election", "Fail");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
    }

    public ResponseEntity<String> createCandidateResults(Long election_id, Long pollingStationId, Long candidateId, Result result, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        Result result1 = new Result();
        result1.setVoteCount(result.getVoteCount());

        Optional<Election> election = electionRepository.findById(election_id);
        if (election.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"election_id","ELection ID not found");
            grpcClient.log(userId.getBody(),"Result Service", "create candidate results", "Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        result1.setElection(election.get());

        Optional<PollingStation> pollingStation = pollingStationRepository.findById(pollingStationId);
        if (pollingStation.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"pollingStationId","Polling station ID not found");
            grpcClient.log(userId.getBody(),"Result Service", "create candidate results", "Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        result1.setPollingStation(pollingStation.get());

        Optional<Candidate> candidate = candidateRepository.findById(candidateId);
        if (candidate.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"candidateId","Candidate ID not found");
            grpcClient.log(userId.getBody(),"Result Service", "create candidate results", "Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        result1.setCandidate(candidate.get());

        resultRepository.save(result1);

        grpcClient.log(userId.getBody(),"Result Service", "create candidate results", "Success");
        return ResponseEntity.ok("Result created successfully");
    }

    public ResponseEntity<String> createListResults(Long election_id, Long pollingStationId, Long listId, Result result, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        Result result1 = new Result();
        result1.setVoteCount(result.getVoteCount());

        Optional<Election> election = electionRepository.findById(election_id);
        if (election.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"election_id","ELection ID not found");
            grpcClient.log(userId.getBody(),"Result Service", "create list results", "Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        result1.setElection(election.get());

        Optional<PollingStation> pollingStation = pollingStationRepository.findById(pollingStationId);
        if (pollingStation.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"pollingStationId","Polling station ID not found");
            grpcClient.log(userId.getBody(),"Result Service", "create list results", "Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        result1.setPollingStation(pollingStation.get());

        Optional<ba.nwt.tim3.models.List> list = listRepository.findById(listId);
        if (list.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"listId","List ID not found");
            grpcClient.log(userId.getBody(),"Result Service", "create list results", "Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        result1.setList(list.get());

        resultRepository.save(result1);

        grpcClient.log(userId.getBody(),"Result Service", "create list results", "Success");
        return ResponseEntity.ok("Result created successfully");
    }

    public ResponseEntity<Integer> getUserId(HttpServletRequest request) {
        HttpEntity<String> entity = getStringHttpEntity(request);

        ResponseEntity<Integer> korisnikId = restTemplate.exchange("http://auth-service/users/id", HttpMethod.GET, entity, Integer.class);
        return korisnikId;
    }
*/
    private static HttpEntity<String> getStringHttpEntity(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // Skip past "Bearer "
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        return entity;
    }

    private final RestTemplate restTemplate;
    private final ResultRepository resultRepository;

    public ResponseEntity<Integer> getUserId(HttpServletRequest request) {
        HttpEntity<String> entity = getStringHttpEntity(request);

        ResponseEntity<Integer> korisnikId = restTemplate.exchange("http://auth-service/users/id", HttpMethod.GET, entity, Integer.class);
        return korisnikId;
    }

    public ResponseEntity getFullElectionResults(String electionName, HttpServletRequest request) {
        List<Result> resultsFirst = resultRepository.getResultsByElectionNameAndPollingStationName(electionName, "Total");
        if (resultsFirst.isEmpty()) {
            HttpEntity<String> entity = getStringHttpEntity(request);
            List<Vote> votes = getVotes(electionName, entity);
            UriComponentsBuilder builder;
            System.out.println(votes);

            for (Vote vote: votes) {
                if (vote.getCandidateId() != null) {
                    builder = UriComponentsBuilder.fromHttpUrl("http://election-microservice/elections/candidate/get-name")
                            .queryParam("candidateId", vote.getCandidateId());
                    ResponseEntity<Candidate> candidateResponseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Candidate.class);
                    Candidate candidate = candidateResponseEntity.getBody();
                    Optional<Result> optionalResult = resultRepository.getResultByCandidateFirstNameAndCandidateLastNameAndElectionNameAndPollingStationName(candidate.getFirstName(), candidate.getLastName(), electionName, "Total");
                    if (optionalResult.isPresent()) {
                        Result result = optionalResult.get();
                        result.setVoteCount(result.getVoteCount() + 1);
                        resultRepository.save(result);
                    }
                    else {
                        Result result = new Result();
                        result.setElectionName(electionName);
                        result.setCandidateFirstName(candidate.getFirstName());
                        result.setCandidateLastName(candidate.getLastName());
                        result.setVoteCount(1);
                        result.setPollingStationName("Total");
                        resultRepository.save(result);
                    }
                }
                else if (vote.getListaId() != null) {
                    builder = UriComponentsBuilder.fromHttpUrl("http://election-microservice/elections/candidate/get-name")
                            .queryParam("listId", vote.getListaId());
                    ResponseEntity<Lista> listResponseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Lista.class);
                    Lista lista = listResponseEntity.getBody();
                    Optional<Result> optionalResult = resultRepository.getResultsByListNameAndElectionNameAndPollingStationName(lista.getName(), electionName, "Total");
                    if (optionalResult.isPresent()) {
                        Result result = optionalResult.get();
                        result.setVoteCount(result.getVoteCount() + 1);
                        resultRepository.save(result);
                    }
                    else {
                        Result result = new Result();
                        result.setElectionName(electionName);
                        result.setListName(lista.getName());
                        result.setVoteCount(1);
                        result.setPollingStationName("Total");
                        resultRepository.save(result);
                    }
                }
            }
            resultsFirst = resultRepository.getResultsByElectionNameAndPollingStationName(electionName, "Total");
            if (resultsFirst.isEmpty()) {
                ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "results", "No results generated because there are no votes");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
            }
            return ResponseEntity.ok(resultsFirst);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(resultsFirst);
        }
    }


    public ResponseEntity getElectionResultsForPollingStation(String electionName, String pollingStationName, HttpServletRequest request) {
        List<Result> resultsFirst = resultRepository.getResultsByElectionNameAndPollingStationName(electionName,pollingStationName);
        if (resultsFirst.isEmpty()) {
            HttpEntity<String> entity = getStringHttpEntity(request);
            List<Vote> votes = getVotes(electionName, entity);
            UriComponentsBuilder builder;
            System.out.println(votes);
            for (Vote vote: votes) {
                builder = UriComponentsBuilder.fromHttpUrl("http://auth-service/pollingStations/user/get-by-id")
                        .queryParam("userId",vote.getVoterId());
                ResponseEntity<PollingStation> pollingStationResponseEntity = restTemplate.exchange(builder.toUriString(),HttpMethod.GET,entity, PollingStation.class);
                if (pollingStationResponseEntity.getBody().getName().equals(pollingStationName)) {
                    if (vote.getCandidateId() != null) {
                        builder = UriComponentsBuilder.fromHttpUrl("http://election-microservice/elections/candidate/get-name")
                                .queryParam("candidateId", vote.getCandidateId());
                        ResponseEntity<Candidate> candidateResponseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Candidate.class);
                        Candidate candidate = candidateResponseEntity.getBody();
                        Optional<Result> optionalResult = resultRepository.getResultByCandidateFirstNameAndCandidateLastNameAndElectionNameAndPollingStationName(candidate.getFirstName(), candidate.getLastName(), electionName, pollingStationName);
                        if (optionalResult.isPresent()) {
                            Result result = optionalResult.get();
                            result.setVoteCount(result.getVoteCount() + 1);
                            resultRepository.save(result);
                        }
                        else {
                            Result result = new Result();
                            result.setElectionName(electionName);
                            result.setCandidateFirstName(candidate.getFirstName());
                            result.setCandidateLastName(candidate.getLastName());
                            result.setVoteCount(1);
                            result.setPollingStationName(pollingStationName);
                            resultRepository.save(result);
                        }
                    }
                    else if (vote.getListaId() != null) {
                        builder = UriComponentsBuilder.fromHttpUrl("http://election-microservice/elections/candidate/get-name")
                                .queryParam("listId", vote.getListaId());
                        ResponseEntity<Lista> listResponseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Lista.class);
                        Lista lista = listResponseEntity.getBody();
                        Optional<Result> optionalResult = resultRepository.getResultsByListNameAndElectionNameAndPollingStationName(lista.getName(), electionName, pollingStationName);
                        if (optionalResult.isPresent()) {
                            Result result = optionalResult.get();
                            result.setVoteCount(result.getVoteCount() + 1);
                            resultRepository.save(result);
                        }
                        else {
                            Result result = new Result();
                            result.setElectionName(electionName);
                            result.setListName(lista.getName());
                            result.setVoteCount(1);
                            result.setPollingStationName(pollingStationName);
                            resultRepository.save(result);
                        }
                    }
                }
            }
            resultsFirst = resultRepository.getResultsByElectionNameAndPollingStationName(electionName, pollingStationName);
            if (resultsFirst.isEmpty()) {
                ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "results", "No results generated because there are no votes");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
            }
            return ResponseEntity.ok(resultsFirst);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(resultsFirst);
        }
    }

    private List<Vote> getVotes(String electionName, HttpEntity<String> entity) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://election-microservice/elections/election/get-id")
                .queryParam("electionName", electionName);
        ResponseEntity<Integer> electionId = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Integer.class);
        builder = UriComponentsBuilder.fromHttpUrl("http://voting-service/voting/votes-for-election")
                .queryParam("electionId", electionId.getBody());
        ResponseEntity<List<Vote>> votesForElection = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<Vote>>() {});
        List<Vote> votes = votesForElection.getBody();
        return votes;
    }

    public ResponseEntity getElectionResultsForCandidate(String electionName, String candidateFirstName, String candidateLastName, String pollingStationName, HttpServletRequest request) {
        Optional<Result> optionalResult = resultRepository.getResultsByElectionNameAndCandidateFirstNameAndCandidateLastNameAndPollingStationName(
                electionName, candidateFirstName, candidateLastName, pollingStationName
        );
        if (optionalResult.isEmpty()) {
            HttpEntity<String> entity = getStringHttpEntity(request);
            List<Vote> votes = getVotes(electionName, entity);
            UriComponentsBuilder builder;
            builder = UriComponentsBuilder.fromHttpUrl("http://election-microservice/elections/get-candidate-by-name")
                    .queryParam("candidateFirstName",candidateFirstName)
                    .queryParam("candidateLastName",candidateLastName);
            ResponseEntity<Candidate> candidateResponseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Candidate.class);
            Candidate candidate = candidateResponseEntity.getBody();
            for (Vote vote: votes) {
                if (vote.getCandidateId() != null && vote.getCandidateId().equals(candidate.getId())) {
                    optionalResult = resultRepository.getResultsByElectionNameAndCandidateFirstNameAndCandidateLastNameAndPollingStationName(electionName,candidateFirstName,candidateLastName,pollingStationName);
                    if (optionalResult.isEmpty()) {
                        Result result = new Result();
                        result.setElectionName(electionName);
                        result.setCandidateFirstName(candidateFirstName);
                        result.setCandidateLastName(candidateLastName);
                        result.setVoteCount(1);
                        result.setPollingStationName(pollingStationName);
                        resultRepository.save(result);
                    }
                    else {
                        Result result = optionalResult.get();
                        result.setVoteCount(result.getVoteCount() + 1);
                        resultRepository.save(result);
                    }
                }
            }
            optionalResult = resultRepository.getResultsByElectionNameAndCandidateFirstNameAndCandidateLastNameAndPollingStationName(electionName, candidateFirstName, candidateLastName, pollingStationName);
            if (optionalResult.isEmpty()) {
                ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "results", "No results generated because there are no votes");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
            }
            return ResponseEntity.ok(optionalResult.get());
        }
        else {
            return ResponseEntity.ok(optionalResult.get());
        }
    }

    public ResponseEntity getElectionResultsForList(String electionName, String listaName, String pollingStationName, HttpServletRequest request) {
        Optional<Result> optionalResult = resultRepository.getResultsByElectionNameAndListNameAndPollingStationName(electionName,listaName,pollingStationName);
        if (optionalResult.isEmpty()) {
            HttpEntity<String> entity = getStringHttpEntity(request);
            List<Vote> votes = getVotes(electionName, entity);
            UriComponentsBuilder builder;
            builder = UriComponentsBuilder.fromHttpUrl("http://election-microservice/elections/get-list-by-name")
                    .queryParam("listaName",listaName);
            Lista lista = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Lista.class).getBody();
            for (Vote vote: votes) {
                if (vote.getListaId() != null && vote.getListaId().equals(lista.getId())) {
                    optionalResult = resultRepository.getResultsByElectionNameAndListNameAndPollingStationName(electionName,listaName,pollingStationName);
                    if (optionalResult.isEmpty()) {
                        Result result = new Result();
                        result.setElectionName(electionName);;
                        result.setListName(listaName);
                        result.setVoteCount(1);
                        result.setPollingStationName(pollingStationName);
                        resultRepository.save(result);
                    }
                    else {
                        Result result = optionalResult.get();
                        result.setVoteCount(result.getVoteCount() + 1);
                        resultRepository.save(result);
                    }
                }
            }
            if (votes.isEmpty()) {
                ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "results", "No results generated because there are no votes");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
            }
            optionalResult = resultRepository.getResultsByElectionNameAndListNameAndPollingStationName(electionName,listaName,pollingStationName);
            return ResponseEntity.ok(optionalResult.get());
        } else {
            return ResponseEntity.ok(optionalResult.get());
        }
    }
}
