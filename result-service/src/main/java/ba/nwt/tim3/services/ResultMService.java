package ba.nwt.tim3.services;


import ba.nwt.tim3.exception.ErrorDetails;
import ba.nwt.tim3.grpc.GrpcClient;
import ba.nwt.tim3.interfaces.ResultRepository;
import ba.nwt.tim3.models.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResultMService {
    private final RestTemplate restTemplate;
    private final ResultRepository resultRepository;
    private static GrpcClient grpcClient;

    private static HttpEntity<String> getStringHttpEntity(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // Skip past "Bearer "
        }

        HttpHeaders headers = new HttpHeaders();
        assert token != null;
        headers.setBearerAuth(token);
        return new HttpEntity<>("body", headers);
    }


    public ResponseEntity<Integer> getUserId(HttpServletRequest request) {
        HttpEntity<String> entity = getStringHttpEntity(request);

        return restTemplate.exchange("http://auth-service/users/id", HttpMethod.GET, entity, Integer.class);
    }

    public ResponseEntity getElectionResultsForPollingStation(String electionName, String pollingStationName, HttpServletRequest request) {
        grpcClient = GrpcClient.get();
        Integer userId = getUserId(request).getBody();

        List<Result> results = resultRepository.getResultsByElectionNameAndPollingStationName(electionName,pollingStationName);
        if (results.isEmpty()) {
            grpcClient.log(userId,"Results", "Get election results for PS", "Fail");
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Get full election results", "No results found for that election");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        grpcClient.log(userId,"Results", "Get election results for PS", "Success");
        return ResponseEntity.ok(results);
    }

    public ResponseEntity getElectionResultsForCandidate(String electionName, String candidateFirstName, String candidateLastName, String pollingStationName, HttpServletRequest request) {
        grpcClient = GrpcClient.get();
        Integer userId = getUserId(request).getBody();
        Optional<Result> optionalResult = resultRepository.getResultsByElectionNameAndCandidateFirstNameAndCandidateLastNameAndPollingStationName(
                electionName, candidateFirstName, candidateLastName, pollingStationName
        );
        if (optionalResult.isEmpty()) {
            grpcClient.log(userId,"Results", "Get election results for candidate", "Fail");
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Get full election results", "No results found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        grpcClient.log(userId,"Results", "Get election results for candidate", "Success");
        return ResponseEntity.ok(optionalResult.get());
    }

    public ResponseEntity getElectionResultsForList(String electionName, String listaName, String pollingStationName, HttpServletRequest request) {
        grpcClient = GrpcClient.get();
        Integer userId = getUserId(request).getBody();

        Optional<Result> optionalResult = resultRepository.getResultsByElectionNameAndListNameAndPollingStationName(
                electionName, listaName, pollingStationName
        );
        if (optionalResult.isEmpty()) {
            grpcClient.log(userId,"Results", "Get election results for list", "Fail");
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Get full election results", "No results found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        grpcClient.log(userId,"Results", "Get election results for list", "Success");
        return ResponseEntity.ok(optionalResult.get());
    }
}