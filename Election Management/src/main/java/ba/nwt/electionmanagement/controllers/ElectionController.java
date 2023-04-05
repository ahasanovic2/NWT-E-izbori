package ba.nwt.electionmanagement.controllers;

import ba.nwt.electionmanagement.entities.*;
import ba.nwt.electionmanagement.services.ElectionService;
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
    public ResponseEntity<String> getElections() {
        return ResponseEntity.ok(electionService.getElections());
    }

    @PostMapping("/create")
    public String createElection(@Valid @RequestBody Election election, RedirectAttributes redirectAttributes) {
        Long id = electionService.createElection(election,redirectAttributes);
        return "redirect:/elections/" + id + "/add-lists";
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
