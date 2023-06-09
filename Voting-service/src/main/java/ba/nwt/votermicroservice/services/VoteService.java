package ba.nwt.votermicroservice.services;


import ba.nwt.votermicroservice.grpc.GrpcClient;
import ba.nwt.votermicroservice.models.Vote;
import ba.nwt.votermicroservice.rabbit.RabbitConfig;
import ba.nwt.votermicroservice.rabbit.VoteMessage;
import ba.nwt.votermicroservice.repositories.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private RestTemplate restTemplate;

    private GrpcClient grpcClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public VoteService() {
        grpcClient = GrpcClient.get();
    }

    public ResponseEntity<Integer> getUserId(HttpServletRequest request) {
        HttpEntity<String> entity = extractEntity(request);

        ResponseEntity<Integer> korisnikId = restTemplate.exchange("http://auth-service/users/id", HttpMethod.GET, entity, Integer.class);
        return korisnikId;
    }

    public ResponseEntity getElectionsForUser(HttpServletRequest request) {
        HttpEntity<String> entity = extractEntity(request);
        ObjectMapper objectMapper = new ObjectMapper();
        String povrat = restTemplate.exchange("http://election-microservice/elections/get-elections-for-user",HttpMethod.GET,entity,String.class).getBody();
        return ResponseEntity.status(HttpStatus.OK).body(povrat);
    }

    private static HttpEntity<String> extractEntity(HttpServletRequest request) {
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

    public ResponseEntity<String> getListsForElection(String name, HttpServletRequest request) {
        System.out.println("Name is " + name);
        HttpEntity<String> entity = extractEntity(request);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://election-microservice/elections/election/lists")
                .queryParam("name", name);
        ResponseEntity<String> lists = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

        return ResponseEntity.status(HttpStatus.OK).body(lists.getBody());
    }

    public ResponseEntity<String> addVoteForCandidate(String electionName, String firstName, String lastName, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        HttpEntity<String> entity = extractEntity(request);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://election-microservice/elections/election/get-id")
                .queryParam("electionName", electionName);
        ResponseEntity<Integer> electionId = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Integer.class);
        builder = UriComponentsBuilder.fromHttpUrl("http://election-microservice/elections/candidate/get-id")
                .queryParam("firstName", firstName).queryParam("lastName", lastName);
        ResponseEntity<Integer> candidateId = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Integer.class);
        Vote vote = new Vote();
        vote.setVoterId(userId.getBody());
        vote.setCandidateId(candidateId.getBody());
        vote.setElectionId(electionId.getBody());
        vote.setTimestamp(LocalDateTime.now().toString());
        voteRepository.save(vote);

        VoteMessage voteMessage = new VoteMessage();
        voteMessage.setId(vote.getId());
        voteMessage.setCandidateId(vote.getCandidateId());
        voteMessage.setTimestamp(vote.getTimestamp());
        voteMessage.setVoterId(vote.getVoterId());
        voteMessage.setElectionId(vote.getElectionId());
        voteMessage.setListaId(null);

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // Skip past "Bearer "
        }

        voteMessage.setToken(token);

        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTING_KEY, voteMessage);


        return ResponseEntity.status(HttpStatus.OK).body("Successfully added vote to candidate");
    }

    public ResponseEntity<String> addVoteForList(String electionName, String name, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        HttpEntity<String> entity = extractEntity(request);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://election-microservice/elections/election/get-id")
                .queryParam("electionName", electionName);
        ResponseEntity<Integer> electionId = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Integer.class);
        builder = UriComponentsBuilder.fromHttpUrl("http://election-microservice/elections/list/get-id")
                .queryParam("name", name);
        ResponseEntity<Integer> listId = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Integer.class);
        Vote vote = new Vote();
        vote.setVoterId(userId.getBody());
        vote.setElectionId(electionId.getBody());
        vote.setListaId(listId.getBody());
        vote.setTimestamp(LocalDateTime.now().toString());
        voteRepository.save(vote);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully added vote to list");
    }

    public ResponseEntity<String> getVoteByElection(Integer electionId, Integer userId, HttpServletRequest request) {
        Optional<Vote> optionalVote = voteRepository.getVoteByElectionIdAndAndVoterId(electionId,userId);
        if (optionalVote.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("{\"hasVote\":false}");
        }
        return ResponseEntity.status(HttpStatus.OK).body("{\"hasVote\":true}");
    }

    public ResponseEntity<String> getAllVotes(HttpServletRequest request) {
        Integer userId = getUserId(request).getBody();
        List<Vote> votes = voteRepository.findAll();
        return getVotesInString(votes, userId);
    }

    public ResponseEntity getAllVotesForElection(Integer electionId, HttpServletRequest request) {
        Integer userId = getUserId(request).getBody();
        List<Vote> votes = voteRepository.findAllByElectionId(electionId);
        return getVotesInString(votes, userId);
    }

    private ResponseEntity getVotesInString(List<Vote> votes, Integer userId) {
        ObjectMapper mapper = new ObjectMapper();
        String votesJsonString;
        try {
            votesJsonString = mapper.writeValueAsString(votes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            grpcClient.log(userId, "Result service", "Parse votes to string", "Fail");
            return new ResponseEntity<>("Failed to convert votes to JSON", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        grpcClient.log(userId, "Result service", "Parse votes to string", "Success");
        return new ResponseEntity<>(votesJsonString, HttpStatus.OK);
    }
}
