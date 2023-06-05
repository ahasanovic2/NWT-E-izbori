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

    @GetMapping("/{electionId}/lists")
    public ResponseEntity<String> getListsByElectionId(@PathVariable Long electionId, HttpServletRequest request){
        return voteService.getListsByElectionId(electionId, request);
    }
    @GetMapping("/{electionId}/lists/{listaId}/candidates")
    public ResponseEntity<String> getCandidatesByListaId(@PathVariable Long electionId, @PathVariable Long listaId, HttpServletRequest request) {
        return voteService.getCandidatesByListaId(electionId,listaId, request);
    }
    @PostMapping("/voter/{voterId}/election/{electionId}/lists/{listaId}/candidates/{candidateId}")
    public ResponseEntity<String> addVoteForCandidateId(@PathVariable Long voterId,
                                                        @PathVariable Long electionId,
                                                        @PathVariable Long listaId,
                                                        @PathVariable Long candidateId,
                                                        HttpServletRequest request) {
        return voteService.addVoteForCandidateId(voterId,electionId,listaId,candidateId,request);
    }

    @GetMapping("/election/get-lists")
    public ResponseEntity<String> getListsForElection(@RequestParam String name, HttpServletRequest request) {
        return voteService.getListsForElection(name,request);
    }

    @GetMapping("/election/list/get-candidates")
    public ResponseEntity<String> getCandidatesForList(@RequestParam String name, HttpServletRequest request) {
        System.out.println("Usao je u voting servis kontroler");
        return voteService.getCandidatesForList(name,request);
    }

}
