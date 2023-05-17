package ba.nwt.votermicroservice.controllers;


import ba.nwt.votermicroservice.exception.ErrorDetails;
import ba.nwt.votermicroservice.grpc.GrpcClient;
import ba.nwt.votermicroservice.repositories.*;
import ba.nwt.votermicroservice.models.Candidate;
import ba.nwt.votermicroservice.models.Lista;
import ba.nwt.votermicroservice.models.Vote;
import ba.nwt.votermicroservice.models.*;
import ba.nwt.votermicroservice.services.VoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.executable.ValidateOnExecution;
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
    VoteService voteService = new VoteService();

    @GetMapping("/{electionId}/lists")
    public ResponseEntity<String> getListsByElectionId(@PathVariable Long electionId){
        GrpcClient.log("lists", "Get all", "Success");
        return voteService.getListsByElectionId(electionId);

    }
    @GetMapping("/{electionId}/lists/{listaId}/candidates")
    public ResponseEntity<String> getCandidatesByListaId(@PathVariable Long electionId, @PathVariable Long listaId) {

        return voteService.getCandidatesByListaId(electionId,listaId);

    }


    @PostMapping("/voter/{voterId}/election/{electionId}/lists/{listaId}/candidates/{candidateId}")
    public ResponseEntity<String> addVoteForCandidateId(@PathVariable Long voterId,
                                                        @PathVariable Long electionId,
                                                        @PathVariable Long listaId,
                                                        @PathVariable Long candidateId) {


        return voteService.addVoteForCandidateId(voterId,electionId,listaId,candidateId);

    }






}
