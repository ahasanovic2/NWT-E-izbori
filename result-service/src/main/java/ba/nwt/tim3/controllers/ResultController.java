package ba.nwt.tim3.controllers;

import ba.nwt.tim3.exception.ResourceNotFoundException;
import ba.nwt.tim3.models.Result;
import ba.nwt.tim3.services.ResultMService;
import com.google.api.Http;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultMService resultMService;

   /* @GetMapping("")
    public ResponseEntity<String> getResults(HttpServletRequest request) {
        return resultMService.getResults(request);
    }

    @GetMapping("/election/{election_id}/pollingStation/{pollingStationId}/candidate/{candidateId}/show")
    public ResponseEntity<String> getCandidateResultsByPollingStation(@PathVariable Long election_id, @PathVariable Long pollingStationId, @PathVariable Long candidateId, HttpServletRequest request) {
        return resultMService.getCandidateResultsByPollingStation(election_id, pollingStationId, candidateId, request);
    }

    @GetMapping("/election/{election_id}/candidate/{candidateId}/show")
    public ResponseEntity<String> getCandidateResultsByElection(@PathVariable Long election_id, @PathVariable Long candidateId, HttpServletRequest request) {
        return resultMService.getCandidateResultsByElection(election_id, candidateId, request);
    }

    @GetMapping("/election/{election_id}/pollingStation/{pollingStationId}/list/{listId}/show")
    public ResponseEntity<String> getListResultsByPollingStation(@PathVariable Long election_id, @PathVariable Long pollingStationId, @PathVariable Long listId, HttpServletRequest request) {
        return resultMService.getListResultsByPollingStation(election_id, pollingStationId, listId, request);
    }

    @GetMapping("/election/{election_id}/list/{listId}/show")
    public ResponseEntity<String> getListResultsByElection(@PathVariable Long election_id, @PathVariable Long listId, HttpServletRequest request) {
        return resultMService.getListResultsByElection(election_id, listId, request);
    }

    @PostMapping("/create/election/{election_id}/pollingstation/{pollingStationId}/candidate/{candidateId}")
    public ResponseEntity<String> createCandidateResults(@PathVariable Long election_id, @PathVariable Long pollingStationId, @PathVariable Long candidateId,@Valid @RequestBody Result result, HttpServletRequest request) {
        return resultMService.createCandidateResults(election_id, pollingStationId, candidateId, result, request);
    }

    @PostMapping("/create/election/{election_id}/pollingstation/{pollingStationId}/list/{listId}")
    public ResponseEntity<String> createListResults(@PathVariable Long election_id, @PathVariable Long pollingStationId, @PathVariable Long listId,@Valid @RequestBody Result result, HttpServletRequest request) {
        return resultMService.createListResults(election_id, pollingStationId, listId, result, request);
    }
*/
    @GetMapping("/full-election")
    public ResponseEntity getFullElectionResults(@RequestParam String electionName, HttpServletRequest request) {
        return resultMService.getFullElectionResults(electionName, request);
    }

    @GetMapping("/election/pollingStation")
    public ResponseEntity getElectionResultsForPollingStation(@RequestParam String electionName, @RequestParam String pollingStationName, HttpServletRequest request) {
        System.out.println("Usao je u kontroler koji treba");
        return resultMService.getElectionResultsForPollingStation(electionName,pollingStationName,request);
    }

    @GetMapping("/election/candidate")
    public ResponseEntity getElectionResultsForCandidate(@RequestParam String electionName,
                                                         @RequestParam String candidateFirstName,
                                                         @RequestParam String candidateLastName,
                                                         HttpServletRequest request) {
        return resultMService.getElectionResultsForCandidate(electionName, candidateFirstName, candidateLastName, "Total", request);
    }

    @GetMapping("/election/pollingStation/candidate")
    public ResponseEntity getElectionResultsForCandidateOnPollingStation(@RequestParam String electionName,
                                                                         @RequestParam String candidateFirstName,
                                                                         @RequestParam String candidateLastName,
                                                                         @RequestParam String pollingStationName,
                                                                         HttpServletRequest request) {
        return resultMService.getElectionResultsForCandidate(electionName, candidateFirstName, candidateLastName, pollingStationName, request);
    }

    @GetMapping("/election/list")
    public ResponseEntity getElectionResultsForList(@RequestParam String electionName,
                                                    @RequestParam String listaName,
                                                    HttpServletRequest request) {
        return resultMService.getElectionResultsForList(electionName,listaName,"Total",request);
    }

    @GetMapping("/election/pollingStation/list")
    public ResponseEntity getElectionResultsForListForPollingStation(@RequestParam String electionName,
                                                                     @RequestParam String listaName,
                                                                     @RequestParam String pollingStationName,
                                                                     HttpServletRequest request) {
        return resultMService.getElectionResultsForList(electionName,listaName,pollingStationName,request);
    }

}
