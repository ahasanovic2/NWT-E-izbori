package ba.nwt.electionmanagement.controllers;

import ba.nwt.electionmanagement.entities.Candidate;
import ba.nwt.electionmanagement.entities.Election;
import ba.nwt.electionmanagement.entities.Lista;
import ba.nwt.electionmanagement.grpc.GrpcClient;
import ba.nwt.electionmanagement.services.ElectionService;
import com.google.api.Http;
import jakarta.persistence.criteria.CriteriaBuilder;
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

    @PostMapping("/create-election")
    public ResponseEntity createElection(@Valid @RequestBody Election election, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        return electionService.createElection(election,redirectAttributes,request);
    }

    @PostMapping("/election/set-pollingstations")
    public ResponseEntity addElectionToPollingStations(@RequestParam String name, @RequestBody List<Long> pollingStationIds, HttpServletRequest request) {
        return electionService.addElectionToPollingStations(name,pollingStationIds, request);
    }

    @GetMapping("/election/pollingstations")
    public ResponseEntity getPollingStations(@RequestParam String name, HttpServletRequest request) {
        return electionService.getPollingStations(name, request);
    }

    @GetMapping("/get-elections-for-user")
    public String getElectionsForUser(HttpServletRequest request) {
        return electionService.getElectionsForUser(request);
    }

    @PostMapping("/election/add-lists")
    public ResponseEntity<String> addLists(@RequestParam String name, @Valid @RequestBody List<Lista> liste, HttpServletRequest request) {
        return electionService.addLists(name,liste, request);
    }


    @GetMapping("/election/lists")
    public ResponseEntity<String> getListsForElections(@RequestParam String name, HttpServletRequest request) {
        return electionService.getListsForElections(name, request);
    }

    @PostMapping("/election/add-candidates")
    public ResponseEntity<String> addCandidates(@RequestParam String name, @Valid @RequestBody List<Candidate> candidates, HttpServletRequest request) {
        return electionService.addCandidates(name, candidates, request);
    }

    @GetMapping("/election/candidates")
    public ResponseEntity<String> getCandidates(@RequestParam String name, HttpServletRequest request) {
        return electionService.getCandidates(name, request);
    }

    @GetMapping("/election/get-id")
    public ResponseEntity getElectionIdByName(@RequestParam String electionName, HttpServletRequest request) {
        return electionService.getElectionIdByName(electionName,request);
    }

    @GetMapping("/candidate/get-id")
    public ResponseEntity getCandidateIdByName(@RequestParam String firstName, @RequestParam String lastName, HttpServletRequest request) {
        return electionService.getCandidateIdByName(firstName, lastName,request);
    }

    @GetMapping("/list/get-id")
    public ResponseEntity getListIdByName(@RequestParam String name, HttpServletRequest request) {
        return electionService.getListIdByName(name,request);
    }
}
