package ba.nwt.tim3.services;


import ba.nwt.results.grpc.GrpcClient;
import ba.nwt.tim3.exception.ErrorDetails;
import ba.nwt.tim3.interfaces.*;
import ba.nwt.tim3.models.Candidate;
import ba.nwt.tim3.models.Election;
import ba.nwt.tim3.models.PollingStation;
import ba.nwt.tim3.models.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<String> getResults() {
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
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error serializing elections to JSON: " + e.getMessage());
            return null;
        }

        return ResponseEntity.ok(json);

    }
    public ResponseEntity<String> getCandidateResultsByPollingStation(Long election_id, Long pollingStationId, Long candidateId) {
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
            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
                GrpcClient.log("Result", "getCandidateResultsByPollingStation", "Fail");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
            }
            return ResponseEntity.ok(json);
        }
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "election_id, pollingStationId, candidateId", "Election id, polling station id or candidate id not found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
    }


    public ResponseEntity<String> getCandidateResultsByElection(Long election_id, Long candidateId) {
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
            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
                GrpcClient.log("Result", "getCandidateResultsByElection", "Fail");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
            }
            return ResponseEntity.ok(json);
        }
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "election_id, candidateId", "Election id or candidate id not found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
    }


    public ResponseEntity<String> getListResultsByPollingStation(Long election_id, Long pollingStationId, Long listId) {
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
            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
                GrpcClient.log("Result", "getListResultsByPollingStation", "Fail");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
            }
            return ResponseEntity.ok(json);
        }
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "election_id, pollingStationId, listId", "Election id, polling station id or list id not found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
    }


    public ResponseEntity<String> getListResultsByElection(Long election_id, Long listId) {
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
            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
                GrpcClient.log("Result", "getListResultsByElection", "Fail");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
            }
            return ResponseEntity.ok(json);
        }
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "election_id, listId", "Election id or list id not found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
    }

    public ResponseEntity<String> createCandidateResults(Long election_id, Long pollingStationId, Long candidateId, Result result) {
        Result result1 = new Result();
        result1.setVoteCount(result.getVoteCount());

        Optional<Election> election = electionRepository.findById(election_id);
        if (election.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"election_id","ELection ID not found");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        result1.setElection(election.get());

        Optional<PollingStation> pollingStation = pollingStationRepository.findById(pollingStationId);
        if (pollingStation.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"pollingStationId","Polling station ID not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        result1.setPollingStation(pollingStation.get());

        Optional<Candidate> candidate = candidateRepository.findById(candidateId);
        if (candidate.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"candidateId","Candidate ID not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        result1.setCandidate(candidate.get());

        resultRepository.save(result1);
        GrpcClient.log("Result", "Create", "Success");
        return ResponseEntity.ok("Result created successfully");
    }

    public ResponseEntity<String> createListResults(Long election_id, Long pollingStationId, Long listId, Result result) {
        Result result1 = new Result();
        result1.setVoteCount(result.getVoteCount());

        Optional<Election> election = electionRepository.findById(election_id);
        if (election.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"election_id","ELection ID not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        result1.setElection(election.get());

        Optional<PollingStation> pollingStation = pollingStationRepository.findById(pollingStationId);
        if (pollingStation.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"pollingStationId","Polling station ID not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        result1.setPollingStation(pollingStation.get());

        Optional<ba.nwt.tim3.models.List> list = listRepository.findById(listId);
        if (list.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"listId","List ID not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        result1.setList(list.get());

        resultRepository.save(result1);
        GrpcClient.log("Result","Create","Success");
        return ResponseEntity.ok("Result created successfully");
    }


}
