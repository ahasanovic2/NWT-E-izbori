package ba.nwt.votermicroservice.services;

import org.springframework.web.client.RestTemplate;
import ba.nwt.votermicroservice.exception.ErrorDetails;
import ba.nwt.votermicroservice.models.*;
import ba.nwt.votermicroservice.repositories.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    private RestTemplate restTemplate1;

    public ResponseEntity<String> getListsByElectionId(Long electionId) {

        Optional<Vote> optionalVote = voteRepository.findById(electionId);
        if(optionalVote.isPresent()){
            Vote vote = optionalVote.get();
            List<Lista> lists = listaRepository.findAllByElectionId(electionId);
            ObjectMapper objectMapper= new ObjectMapper();
            String json = null;
            try{
                json = objectMapper.writeValueAsString(lists);
            }
            catch (JsonProcessingException e){
                e.printStackTrace();
            }
            return ResponseEntity.ok(json);
        }
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"electionId","Election ID not found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
    }

    public ResponseEntity<String> getCandidatesByListaId(Long electionId, Long listaId) {
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
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return ResponseEntity.ok(json);
            }
        }
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"listaId","Lista ID not found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
    }

    public ResponseEntity<String> addVoteForCandidateId(Long voterId, Long electionId, Long listaId, Long candidateId) {
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

                        voteRepository.save(vote); // save the updated Vote object to the database
                        return ResponseEntity.ok("Glas uspjesno dodan!");
                    } else {
                        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"voterId","Voter ID not found");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
                    }
                } else {
                    ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"candidateId","Candidate ID not found");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
                }
            } else {
                ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"listaId","Lista ID not found");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
            }
        } else {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"electionId","Election ID not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
    }

    public ResponseEntity<String> getElectionsByPollingStationId(Long pollingStationId) {
        String electionManagementUrl = "http://election-management/elections/getElectionsByPollingStationId" + pollingStationId;
        ResponseEntity<String> response = restTemplate1.getForEntity(electionManagementUrl, String.class);
        System.out.println(response.getBody());
        return response;
    }





}
