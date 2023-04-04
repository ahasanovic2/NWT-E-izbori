package ba.nwt.tim3.controllers;

import ba.nwt.tim3.exception.ResourceNotFoundException;
import ba.nwt.tim3.interfaces.PollingStationRepository;
import ba.nwt.tim3.interfaces.ResultRepository;
import ba.nwt.tim3.models.PollingStation;
import ba.nwt.tim3.models.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired PollingStationRepository pollingStationRepository;

    @GetMapping("")
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
        }


        return ResponseEntity.ok(json);

    }


    @GetMapping("/{pollingStationId}/get-election-result")
    public ResponseEntity<Result> getElectionResult(@PathVariable Long pollingStationId) throws ResourceNotFoundException {
        Result electionResult = resultRepository.findById(pollingStationId).orElseThrow(() -> new ResourceNotFoundException("Election result not find for this id :" +pollingStationId));
        return ResponseEntity.ok().body(electionResult);
    }

    @GetMapping("/{election_id}/lists/{listId}")
    public ResponseEntity<Result> getListResult(@PathVariable Long election_id, @PathVariable Long listId) throws ResourceNotFoundException {
        Optional<Result> listResult = resultRepository.findByElectionIdAndListId(election_id, listId);
        if (listResult.isPresent()) {
            return ResponseEntity.ok().body(listResult.get());
        } else {
            throw new ResourceNotFoundException("List result not found for list id:"+listId+" and election id:"+election_id);
        }
    }

    @GetMapping("/{election_id}/candidates/{candidateId}")
    public ResponseEntity<Result> getCandidateResult(@PathVariable Long election_id, @PathVariable Long candidateId) throws ResourceNotFoundException {
        Optional<Result> candidateResult = resultRepository.findByElectionIdAndCandidateId(election_id, candidateId);
        if (candidateResult.isPresent()) {
            return ResponseEntity.ok().body(candidateResult.get());
        } else {
            throw new ResourceNotFoundException("Candidate result not found for this id:"+candidateId);
        }
    }
}
