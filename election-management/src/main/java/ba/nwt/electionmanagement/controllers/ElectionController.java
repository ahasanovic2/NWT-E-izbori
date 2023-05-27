package ba.nwt.electionmanagement.controllers;

import ba.nwt.electionmanagement.entities.*;
import ba.nwt.electionmanagement.grpc.GrpcClient;
import ba.nwt.electionmanagement.services.ElectionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/elections")
public class ElectionController {

    @Autowired
    private ElectionService electionService = new ElectionService();

    @GetMapping("")
    public ResponseEntity<String> getElections() {
        String povrat = electionService.getElections();
        if (povrat != null)
            GrpcClient.log("Elections", "Get all", "Success");
        else
            GrpcClient.log("Elections", "Get all", "Fail");
        return ResponseEntity.ok(electionService.getElections());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createElection(@Valid @RequestBody Election election, RedirectAttributes redirectAttributes) {
        return electionService.createElection(election,redirectAttributes);
    }

    @PostMapping("/{electionId}/pollingStations")
    public ResponseEntity<String> addElectionToPollingStations(@PathVariable Long electionId, @RequestBody List<Long> pollingStationIds) {
        return electionService.addElectionToPollingStations(electionId,pollingStationIds);
    }

    @GetMapping("/{electionId}/pollingStations")
    public ResponseEntity<String> getPollingStations(@PathVariable Long electionId) {
        return electionService.getPollingStations(electionId);
    }

    @PostMapping("/{electionId}/add-lists")
    public ResponseEntity<String> addLists(@PathVariable Long electionId, @Valid @RequestBody List<Lista> liste) {
        return electionService.addLists(electionId,liste);
    }


    @GetMapping("/{electionId}/lists")
    public ResponseEntity<String> getListsForElections(@PathVariable Long electionId) {
        return electionService.getListsForElections(electionId);
    }


    @PostMapping("/{electionId}/lists/{listId}/add-candidates")
    public ResponseEntity<String> addCandidates(@PathVariable Long electionId, @PathVariable Long listId, @Valid @RequestBody List<Candidate> candidates) {
        return electionService.addCandidates(electionId,listId,candidates);
    }

    @GetMapping("/{electionId}/lists/{listId}/candidates")
    public ResponseEntity<String> getCandidates(@PathVariable Long electionId, @PathVariable Long listId) {
        return electionService.getCandidates(electionId,listId);
    }
}
