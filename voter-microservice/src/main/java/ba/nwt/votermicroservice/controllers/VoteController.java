package ba.nwt.votermicroservice.controllers;


import ba.nwt.votermicroservice.exception.ErrorDetails;
import ba.nwt.votermicroservice.repositories.*;
import ba.nwt.votermicroservice.models.Candidate;
import ba.nwt.votermicroservice.models.Lista;
import ba.nwt.votermicroservice.models.Vote;
import ba.nwt.votermicroservice.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/voting")
public class VoteController {

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

    @GetMapping("/{electionId}/lists")
    public ResponseEntity<String> getListsByElectionId(@PathVariable Long electionId){
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
        else return new ResponseEntity<>("Ne postoje izbori sa zadanim ID-em", HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/{electionId}/lists/{listaId}/candidates")
    public ResponseEntity<String> getCandidatesByListaId(@PathVariable Long electionId, @PathVariable Long listaId) {
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
        return new ResponseEntity<>("Ne postoji lista sa zadanim ID-em na zadanim izborima!", HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/{voterId}/{electionId}/lists/{listaId}/candidates/{candidateId}")
    public ResponseEntity<String> addVoteForCandidateId(@PathVariable Long voterId,
                                                        @PathVariable Long electionId,
                                                        @PathVariable Long listaId,
                                                        @PathVariable Long candidateId) {
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






}
