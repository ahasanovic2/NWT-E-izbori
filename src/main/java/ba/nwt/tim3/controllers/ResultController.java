package ba.nwt.tim3.controllers;

import ba.nwt.tim3.exception.ResourceNotFoundException;
import ba.nwt.tim3.models.Result;
import ba.nwt.tim3.services.ResultMService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultMService resultMService = new ResultMService();

    @GetMapping("")
    public ResponseEntity<ResponseEntity<String>> getResults() {
        return ResponseEntity.ok(resultMService.getResults());
    }

    @GetMapping("/election/{election_id}/pollingStation/{pollingStationId}/candidate/{candidateId}/show")
    public ResponseEntity<String> getCandidateResultsByPollingStation(@PathVariable Long election_id, @PathVariable Long pollingStationId, @PathVariable Long candidateId) {
        return resultMService.getCandidateResultsByPollingStation(election_id, pollingStationId, candidateId);
    }

    @GetMapping("/election/{election_id}/candidate/{candidateId}/show")
    public ResponseEntity<String> getCandidateResultsByElection(@PathVariable Long election_id, @PathVariable Long candidateId) {
        return resultMService.getCandidateResultsByElection(election_id, candidateId);
    }

    @GetMapping("/election/{election_id}/pollingStation/{pollingStationId}/list/{listId}/show")
    public ResponseEntity<String> getListResultsByPollingStation(@PathVariable Long election_id, @PathVariable Long pollingStationId, @PathVariable Long listId) {
        return resultMService.getListResultsByPollingStation(election_id, pollingStationId, listId);
    }

    @GetMapping("/election/{election_id}/list/{listId}/show")
    public ResponseEntity<String> getListResultsByElection(@PathVariable Long election_id, @PathVariable Long listId) {
        return resultMService.getListResultsByElection(election_id, listId);
    }

    @PostMapping("/create/election/{election_id}/pollingstation/{pollingStationId}/candidate/{candidateId}")
    public ResponseEntity<String> createCandidateResults(@PathVariable Long election_id, @PathVariable Long pollingStationId, @PathVariable Long candidateId,@Valid @RequestBody Result result) {
        return resultMService.createCandidateResults(election_id, pollingStationId, candidateId, result);
    }

    @PostMapping("/create/election/{election_id}/pollingstation/{pollingStationId}/list/{listId}")
    public ResponseEntity<String> createListResults(@PathVariable Long election_id, @PathVariable Long pollingStationId, @PathVariable Long listId,@Valid @RequestBody Result result) {
        return resultMService.createListResults(election_id, pollingStationId, listId, result);
    }

}
