package ba.nwt.electionmanagement.controllers;

import ba.nwt.electionmanagement.interfaces.ElectionRepository;
import ba.nwt.electionmanagement.models.Election;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/elections")
public class ElectionController {

    @Autowired
    private ElectionRepository electionRepository;

    @PostMapping("/create")
    public String createElection(@RequestBody Election election) {
        System.out.println(election.getName());
        System.out.println(election.getStartTime());
        System.out.println(election.getEndTime().getDayOfMonth());
        electionRepository.save(election);
        return "redirect:/elections/" + election.getId();
    }


}
