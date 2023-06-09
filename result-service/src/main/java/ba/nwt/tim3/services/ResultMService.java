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
    private final RestTemplate restTemplate;
    private final ResultRepository resultRepository;

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
                    builder = UriComponentsBuilder.fromHttpUrl("http://election-microservice/elections/list/get-name")
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
                        builder = UriComponentsBuilder.fromHttpUrl("http://election-microservice/elections/list/get-name")
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
