package ba.nwt.votermicroservice.controllers;


import ba.nwt.votermicroservice.services.VoteService;
import com.google.api.Http;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voting")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @GetMapping("/elections")
    public ResponseEntity getElectionsForUser(HttpServletRequest request) {
        return voteService.getElectionsForUser(request);
    }

    @PostMapping("/vote-for-candidate")
    public ResponseEntity<String> addVoteForCandidate(@RequestParam String electionName,
                                                      @RequestParam String firstName,
                                                      @RequestParam String lastName,
                                                      HttpServletRequest request) {
        return voteService.addVoteForCandidate(electionName, firstName, lastName, request);
    }

    @PostMapping("/vote-for-list")
    public ResponseEntity<String> addVoteForList(@RequestParam String electionName,
                                                 @RequestParam String name,
                                                 HttpServletRequest request) {
        return voteService.addVoteForList(electionName, name, request);
    }

    @GetMapping("/election/get-lists")
    public ResponseEntity<String> getListsForElection(@RequestParam String name, HttpServletRequest request) {
        return voteService.getListsForElection(name,request);
    }

    @GetMapping("/get-vote-by-election")
    public ResponseEntity<String> getVoteByElection(@RequestParam Integer electionId, @RequestParam Integer voterId, HttpServletRequest request) {
        return voteService.getVoteByElection(electionId, voterId, request);
    }

    @GetMapping("/votes")
    public ResponseEntity getAllVotes(HttpServletRequest request) {
        return voteService.getAllVotes(request);
    }

    @GetMapping("/votes-for-election")
    public ResponseEntity allVotes(@RequestParam Integer electionId, HttpServletRequest request) {
        return voteService.getAllVotesForElection(electionId,request);
    }
}
