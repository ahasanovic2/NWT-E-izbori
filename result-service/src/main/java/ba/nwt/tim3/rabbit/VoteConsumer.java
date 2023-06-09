package ba.nwt.tim3.rabbit;

import ba.nwt.tim3.exception.ErrorDetails;
import ba.nwt.tim3.interfaces.ResultRepository;
import ba.nwt.tim3.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.parser.Entity;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VoteConsumer {
    private final ResultRepository resultRepository;
    private final RabbitTemplate rabbitTemplate;

    private final RestTemplate restTemplate;

    private HttpEntity<String> extractEntity(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>("body",headers);
        return entity;
    }

    private PollingStation getPollingStation(Integer userId, String token) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://auth-service/pollingStations/user/get-by-id")
                .queryParam("userId",userId);
        HttpEntity<String> entity = extractEntity(token);
        ResponseEntity<PollingStation> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, PollingStation.class);
        PollingStation pollingStation = responseEntity.getBody();
        return pollingStation;
    }

    private Candidate getCandidate(Integer candidateId, String token) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://election-microservice/elections/candidate/get-name")
                .queryParam("candidateId", candidateId);
        HttpEntity<String> entity = extractEntity(token);
        ResponseEntity<Candidate> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Candidate.class);
        Candidate candidate = responseEntity.getBody();
        return candidate;

    }

    private Lista getLista(Integer listaId, String token) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://election-microservice/elections/list/get-name")
                .queryParam("listId", listaId);
        HttpEntity<String> entity = extractEntity(token);
        ResponseEntity<Lista> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Lista.class);
        Lista lista = responseEntity.getBody();
        return  lista;
    }



    private Election getElection(Integer electionId, String token) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://election-microservice/elections/get-election-by-id")
                .queryParam("electionId", electionId);
        HttpEntity<String> entity = extractEntity(token);
        ResponseEntity<Election> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Election.class);
        Election election = responseEntity.getBody();
        return election;
    }

    @RabbitListener(queues = "voting-queue")
    public void consumeMessageFromQueue(VoteMessage voteMessage) {
        HttpEntity<String> entity = extractEntity(voteMessage.getToken());
        PollingStation pollingStation = getPollingStation(voteMessage.getVoterId(), voteMessage.getToken());
        Election election = getElection(voteMessage.getElectionId(), voteMessage.getToken());
        if (voteMessage.getCandidateId() != null) {
            Candidate candidate = getCandidate(voteMessage.getCandidateId(), voteMessage.getToken());
            Optional<Result> optionalResult = resultRepository.getResultsByElectionNameAndCandidateFirstNameAndCandidateLastNameAndPollingStationName(
                    election.getName(), candidate.getFirstName(), candidate.getLastName(), pollingStation.getName()
            );
            if (optionalResult.isEmpty()) {
                Result result = new Result();
                result.setElectionName(election.getName());
                result.setCandidateLastName(candidate.getLastName());
                result.setCandidateFirstName(candidate.getFirstName());
                result.setVoteCount(1);
                result.setPollingStationName(pollingStation.getName());
                resultRepository.save(result);

                Result result1 = new Result();
                result1.setElectionName(election.getName());
                result1.setCandidateLastName(candidate.getLastName());
                result1.setCandidateFirstName(candidate.getFirstName());
                result1.setVoteCount(1);
                result1.setPollingStationName("Total");
                resultRepository.save(result1);
            }
            else {
                Result result = optionalResult.get();
                result.setVoteCount(result.getVoteCount() + 1);
                resultRepository.save(result);

                optionalResult = resultRepository.getResultsByElectionNameAndCandidateFirstNameAndCandidateLastNameAndPollingStationName(
                        election.getName(), candidate.getFirstName(), candidate.getLastName(), "Total"
                );
                Result result1 = optionalResult.get();
                result1.setVoteCount(result1.getVoteCount() + 1);
                resultRepository.save(result1);
            }
        }
        else if (voteMessage.getListaId() != null) {
            Lista lista = getLista(voteMessage.getListaId(), voteMessage.getToken());
            Optional<Result> optionalResult = resultRepository.getResultsByElectionNameAndListNameAndPollingStationName(
                    election.getName(), lista.getName(), pollingStation.getName()
            );
            if (optionalResult.isEmpty()) {
                Result result = new Result();
                result.setElectionName(election.getName());
                result.setListName(lista.getName());
                result.setVoteCount(1);
                result.setPollingStationName(pollingStation.getName());
                resultRepository.save(result);

                Result result1 = new Result();
                result1.setElectionName(election.getName());
                result1.setListName(lista.getName());
                result1.setVoteCount(1);
                result1.setPollingStationName("Total");
                resultRepository.save(result1);
            }
            else {
                Result result = optionalResult.get();
                result.setVoteCount(result.getVoteCount() + 1);
                resultRepository.save(result);

                optionalResult = resultRepository.getResultsByElectionNameAndListNameAndPollingStationName(
                        election.getName(), lista.getName(), "Total"
                );
                Result result1 = optionalResult.get();
                result1.setVoteCount(result1.getVoteCount() + 1);
                resultRepository.save(result1);
            }
        }
        return;
    }


}
