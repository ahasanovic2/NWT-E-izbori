package ba.nwt.electionmanagement.controllers;

import ba.nwt.electionmanagement.interfaces.ElectionRepository;
import ba.nwt.electionmanagement.models.Election;
import ba.nwt.electionmanagement.models.Lista;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/elections")
public class ElectionController {

    @Autowired
    private ElectionRepository electionRepository;

    @GetMapping("")
    public String getElections() {
        List<Election> elections = electionRepository.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(elections);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    @PostMapping("/create")
    public String createElection(@RequestBody Election election, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("electionId", election.getId());
        electionRepository.save(election);
        return "redirect:/elections/" + election.getId() + "/add-lists";
    }

    @GetMapping("/{electionId}/add-lists")
    public String addCandidatesAndLists(@PathVariable Long electionId, Model model) {
        // Retrieve the election from the database using the ID
        Optional<Election> optionalElection = electionRepository.findById(electionId);
        if (optionalElection.isPresent()) {
            Election election = optionalElection.get();
            model.addAttribute("election", election);
            return "add-lists"; // Return the name of the HTML file for the add candidates and lists page
        } else {
            // Handle case where election with given ID does not exist
            return "redirect:/elections";
        }
    }

    @PostMapping("/{electionId}/add-lists")
    public String addLists(@PathVariable Long electionId, @RequestBody List<Lista> liste) {
        for (Lista lista : liste)
            System.out.println(lista.getName());
        return "Uspjesno izvrsena ruta";
    }



}
