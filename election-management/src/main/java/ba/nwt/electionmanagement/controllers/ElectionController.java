package ba.nwt.electionmanagement.controllers;

import ba.nwt.electionmanagement.entities.Candidate;
import ba.nwt.electionmanagement.entities.Election;
import ba.nwt.electionmanagement.entities.Lista;
import ba.nwt.electionmanagement.grpc.GrpcClient;
import ba.nwt.electionmanagement.services.ElectionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/elections")
public class ElectionController {

    @Autowired
    private ElectionService electionService = new ElectionService();

    @GetMapping("")
    public ResponseEntity<String> getElections(HttpServletRequest request) {
        ResponseEntity<Integer> korisnikId = electionService.getUserId(request);
        String povrat = electionService.getElections();
        if (povrat != null)
            GrpcClient.log(korisnikId.getBody(),"Elections", "Get all", "Success");
        else
            GrpcClient.log(korisnikId.getBody(),"Elections", "Get all", "Fail");
        return ResponseEntity.ok(electionService.getElections());
    }

    @PostMapping("/create")
    public ResponseEntity createElection(@Valid @RequestBody Election election, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        return electionService.createElection(election,redirectAttributes,request);
    }

    @PostMapping("/{electionId}/pollingStations")
    public ResponseEntity addElectionToPollingStations(@PathVariable Long electionId, @RequestBody List<Long> pollingStationIds, HttpServletRequest request) {
        return electionService.addElectionToPollingStations(electionId,pollingStationIds, request);
    }

    @GetMapping("/{electionId}/pollingStations")
    public ResponseEntity getPollingStations(@PathVariable Long electionId, HttpServletRequest request) {
        return electionService.getPollingStations(electionId, request);
    }

    @GetMapping("/get-elections-for-user")
    public ResponseEntity getElectionsForUser(HttpServletRequest request) {
        return electionService.getElectionsForUser(request);
    }

    @PostMapping("/{electionId}/add-lists")
    public ResponseEntity<String> addLists(@PathVariable Long electionId, @Valid @RequestBody List<Lista> liste, HttpServletRequest request) {
        return electionService.addLists(electionId,liste, request);
    }


    @GetMapping("/{electionId}/lists")
    public ResponseEntity<String> getListsForElections(@PathVariable Long electionId, HttpServletRequest request) {
        return electionService.getListsForElections(electionId, request);
    }


    @PostMapping("/{electionId}/lists/{listId}/add-candidates")
    public ResponseEntity<String> addCandidates(@PathVariable Long electionId, @PathVariable Long listId, @Valid @RequestBody List<Candidate> candidates, HttpServletRequest request) {
        return electionService.addCandidates(electionId,listId,candidates, request);
    }

    @GetMapping("/{electionId}/lists/{listId}/candidates")
    public ResponseEntity<String> getCandidates(@PathVariable Long electionId, @PathVariable Long listId, HttpServletRequest request) {
        return electionService.getCandidates(electionId,listId, request);
    }
}
