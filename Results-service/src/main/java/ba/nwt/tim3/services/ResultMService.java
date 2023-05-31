package ba.nwt.tim3.services;


import ba.nwt.tim3.exception.ErrorDetails;
import ba.nwt.tim3.grpc.GrpcClient;
import ba.nwt.tim3.interfaces.*;
import ba.nwt.tim3.models.Candidate;
import ba.nwt.tim3.models.Election;
import ba.nwt.tim3.models.PollingStation;
import ba.nwt.tim3.models.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.Http;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ResultMService {

    @Autowired
    private ResultRepository resultRepository;

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
            GrpcClient.log(userId.getBody(),"Result Service", "get results", "Success");
            return ResponseEntity.ok(json);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error serializing elections to JSON: " + e.getMessage());
            GrpcClient.log(userId.getBody(),"Result Service", "get results", "Fail");
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
                GrpcClient.log(userId.getBody(),"Result Service", "get candidate result by polling station", "Success");
                return ResponseEntity.ok(json);
            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
                GrpcClient.log(userId.getBody(),"Result Service", "get candidate result by polling station", "Fail");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
            }
        }
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "election_id, pollingStationId, candidateId", "Election id, polling station id or candidate id not found");
        GrpcClient.log(userId.getBody(),"Result Service", "get candidate result by polling station", "Fail");
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
                GrpcClient.log(userId.getBody(),"Result Service", "get candidate result by election", "Success");
                return ResponseEntity.ok(json);
            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
                GrpcClient.log(userId.getBody(),"Result Service", "get candidate result by election", "Fail");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
            }
        }
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "election_id, candidateId", "Election id or candidate id not found");
        GrpcClient.log(userId.getBody(),"Result Service", "get candidate result by election", "Fail");
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
                GrpcClient.log(userId.getBody(),"Result Service", "get list results by polling station", "Success");
                return ResponseEntity.ok(json);
            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
                GrpcClient.log(userId.getBody(),"Result Service", "get list results by polling station", "Fail");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
            }
        }
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "election_id, pollingStationId, listId", "Election id, polling station id or list id not found");
        GrpcClient.log(userId.getBody(),"Result Service", "get list results by polling station", "Fail");
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
                GrpcClient.log(userId.getBody(),"Result Service", "get list result by election", "Success");
                return ResponseEntity.ok(json);
            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
                GrpcClient.log(userId.getBody(),"Result Service", "get list result by election", "Fail");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
            }
        }
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "election_id, listId", "Election id or list id not found");
        GrpcClient.log(userId.getBody(),"Result Service", "get list result by election", "Fail");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
    }

    public ResponseEntity<String> createCandidateResults(Long election_id, Long pollingStationId, Long candidateId, Result result, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        Result result1 = new Result();
        result1.setVoteCount(result.getVoteCount());

        Optional<Election> election = electionRepository.findById(election_id);
        if (election.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"election_id","ELection ID not found");
            GrpcClient.log(userId.getBody(),"Result Service", "create candidate results", "Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        result1.setElection(election.get());

        Optional<PollingStation> pollingStation = pollingStationRepository.findById(pollingStationId);
        if (pollingStation.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"pollingStationId","Polling station ID not found");
            GrpcClient.log(userId.getBody(),"Result Service", "create candidate results", "Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        result1.setPollingStation(pollingStation.get());

        Optional<Candidate> candidate = candidateRepository.findById(candidateId);
        if (candidate.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"candidateId","Candidate ID not found");
            GrpcClient.log(userId.getBody(),"Result Service", "create candidate results", "Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        result1.setCandidate(candidate.get());

        resultRepository.save(result1);

        GrpcClient.log(userId.getBody(),"Result Service", "create candidate results", "Success");
        return ResponseEntity.ok("Result created successfully");
    }

    public ResponseEntity<String> createListResults(Long election_id, Long pollingStationId, Long listId, Result result, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        Result result1 = new Result();
        result1.setVoteCount(result.getVoteCount());

        Optional<Election> election = electionRepository.findById(election_id);
        if (election.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"election_id","ELection ID not found");
            GrpcClient.log(userId.getBody(),"Result Service", "create list results", "Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        result1.setElection(election.get());

        Optional<PollingStation> pollingStation = pollingStationRepository.findById(pollingStationId);
        if (pollingStation.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"pollingStationId","Polling station ID not found");
            GrpcClient.log(userId.getBody(),"Result Service", "create list results", "Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        result1.setPollingStation(pollingStation.get());

        Optional<ba.nwt.tim3.models.List> list = listRepository.findById(listId);
        if (list.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"listId","List ID not found");
            GrpcClient.log(userId.getBody(),"Result Service", "create list results", "Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        result1.setList(list.get());

        resultRepository.save(result1);

        GrpcClient.log(userId.getBody(),"Result Service", "create list results", "Success");
        return ResponseEntity.ok("Result created successfully");
    }

    public ResponseEntity<Integer> getUserId(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // Skip past "Bearer "
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<Integer> korisnikId = restTemplate.exchange("http://auth-service/users/id", HttpMethod.GET, entity, Integer.class);
        return korisnikId;
    }

}
