package ba.nwt.votermicroservice.services;


import ba.nwt.votermicroservice.exception.ErrorDetails;
import ba.nwt.votermicroservice.grpc.GrpcClient;
import ba.nwt.votermicroservice.models.Candidate;
import ba.nwt.votermicroservice.models.Lista;
import ba.nwt.votermicroservice.models.Vote;
import ba.nwt.votermicroservice.models.Voter;
import ba.nwt.votermicroservice.repositories.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.Http;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    private PollingStationRepository pollingStationRepository;

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> getListsByElectionId(Long electionId, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        Optional<Vote> optionalVote = voteRepository.findById(electionId);
        if(optionalVote.isPresent()){
            Vote vote = optionalVote.get();
            List<Lista> lists = listaRepository.findAllByElectionId(electionId);
            ObjectMapper objectMapper= new ObjectMapper();
            String json = null;
            try{
                json = objectMapper.writeValueAsString(lists);
                GrpcClient.log(userId.getBody(),"Voting service","get lists by election ID", "Success");
                return ResponseEntity.ok(json);
            }
            catch (JsonProcessingException e){
                GrpcClient.log(userId.getBody(),"Voting service","get lists by election ID", "Fail");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
            }
        }
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now().toString(),"electionId","Election ID not found");
        GrpcClient.log(userId.getBody(),"Voting service","get lists by election ID", "Fail");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
    }

    public ResponseEntity<String> getCandidatesByListaId(Long electionId, Long listaId, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        Optional<Vote> optionalVote = voteRepository.findById(electionId);
        if (optionalVote.isPresent()) {
            Vote vote = optionalVote.get();
            List<Lista> lists = listaRepository.findAllByElectionId(electionId);
            Optional<Lista> optionalLista = lists.stream().filter(l -> l.getId().equals(listaId)).findFirst();
            if (optionalLista.isPresent()) {
                Lista lista = optionalLista.get();
                List<Candidate> candidates = candidateRepository.findAllByListaId(listaId);
                ObjectMapper objectMapper = new ObjectMapper();
                String json = null;
                try {
                    json = objectMapper.writeValueAsString(candidates);
                    GrpcClient.log(userId.getBody(),"Voting service","get candidates by lista ID", "Success");
                    return ResponseEntity.ok(json);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    GrpcClient.log(userId.getBody(),"Voting service","get candidates by lista ID", "Fail");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
                }
            }
        }
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now().toString(),"listaId","Lista ID not found");
        GrpcClient.log(userId.getBody(),"Voting service","get candidates by lista ID", "Fail");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
    }

    public ResponseEntity<String> addVoteForCandidateId(Long voterId, Long electionId, Long listaId, Long candidateId, HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        Optional<Vote> optionalVote = voteRepository.findById(electionId);
        if (optionalVote.isPresent()) {
            Vote vote = optionalVote.get();
            List<Lista> lists = listaRepository.findAllByElectionId(electionId);
            Optional<Lista> optionalLista = lists.stream().filter(l -> l.getId().equals(listaId)).findFirst();
            if (optionalLista.isPresent()) {
                Lista lista = optionalLista.get();
                Optional<Candidate> optionalCandidate = candidateRepository.findByIdAndListaId(candidateId, listaId);
                if (optionalCandidate.isPresent()) {
                    Candidate candidate = optionalCandidate.get();
                    Optional<Voter> optionalVoter = voterRepository.findById(voterId);
                    if (optionalVoter.isPresent()) {
                        Voter voter = optionalVoter.get();

                        vote.setVoter(voter);
                        vote.setCandidate(candidate);
                        GrpcClient.log(userId.getBody(),"Voting service","add vote for candidate id", "Success");
                        voteRepository.save(vote); // save the updated Vote object to the database
                        return ResponseEntity.ok("Glas uspjesno dodan!");
                    } else {
                        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now().toString(),"voterId","Voter ID not found");
                        GrpcClient.log(userId.getBody(),"Voting service","add vote for candidate id", "Fail");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
                    }
                } else {
                    ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now().toString(),"candidateId","Candidate ID not found");
                    GrpcClient.log(userId.getBody(),"Voting service","add vote for candidate id", "Fail");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
                }
            } else {
                ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now().toString(),"listaId","Lista ID not found");
                GrpcClient.log(userId.getBody(),"Voting service","add vote for candidate id", "Fail");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
            }
        } else {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now().toString(),"electionId","Election ID not found");
            GrpcClient.log(userId.getBody(),"Voting service","add vote for candidate id", "Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
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
        HttpEntity<String> entity = extractEntity(request);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://election-microservice/elections/election/get-lists")
                .queryParam("name", name);
        ResponseEntity<String> lists = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

        return ResponseEntity.status(HttpStatus.OK).body(lists.getBody());
    }

    public ResponseEntity<String> getCandidatesForList(String name, HttpServletRequest request) {
        HttpEntity<String> entity = extractEntity(request);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://election-microservice/elections/election/list/get-candidates")
                .queryParam("name", name);
        ResponseEntity<String> candidates = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

        return ResponseEntity.status(HttpStatus.OK).body(candidates.getBody());
    }


}
