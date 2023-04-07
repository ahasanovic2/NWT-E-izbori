package ba.nwt.tim3.controllers;

import ba.nwt.tim3.exception.ResourceNotFoundException;
import ba.nwt.tim3.models.Result;
import ba.nwt.tim3.services.ResultMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultMService resultMService = new ResultMService();

    @GetMapping("")
    public ResponseEntity<ResponseEntity<String>> getResults() {
        return ResponseEntity.ok(resultMService.getResults());
    }

    @GetMapping("/{pollingStationId}/get-election-result")
    public ResponseEntity<Result> getElectionResult(@PathVariable Long pollingStationId) throws ResourceNotFoundException {
        return resultMService.getElectionResult(pollingStationId);
    }

    @GetMapping("/{election_id}/lists/{listId}")
    public ResponseEntity<Result> getListResult(@PathVariable Long election_id, @PathVariable Long listId) throws ResourceNotFoundException {
        return resultMService.getListResult(election_id, listId);
    }

    @GetMapping("/{election_id}/candidates/{candidateId}")
    public ResponseEntity<Result> getCandidateResult(@PathVariable Long election_id, @PathVariable Long candidateId) throws ResourceNotFoundException {
        return resultMService.getCandidateResult(election_id, candidateId);
    }

}
