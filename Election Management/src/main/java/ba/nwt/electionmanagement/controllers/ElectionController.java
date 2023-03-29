package ba.nwt.electionmanagement.controllers;

import ba.nwt.electionmanagement.interfaces.ElectionRepository;
import ba.nwt.electionmanagement.interfaces.ListaRepository;
import ba.nwt.electionmanagement.models.Election;
import ba.nwt.electionmanagement.models.Kandidat;
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

    @Autowired
    private ListaRepository listaRepository;

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

    @PostMapping("/{electionId}/add-lists")
    public String addLists(@PathVariable Long electionId, @RequestBody List<Lista> liste) {
        Optional<Election> optionalElection = electionRepository.findById(electionId);
        if (optionalElection.isPresent()) {
            Election election = optionalElection.get();
            for (Lista lista : liste) {
                lista.setElection(election);
                listaRepository.save(lista);
            }
        }
        return "Uspjesno izvrsena ruta";
    }

    @GetMapping("/{electionId}/lists")
    public String getListsForElections(@PathVariable Long electionId) {
        Optional<Election> optionalElection = electionRepository.findById(electionId);
        if (optionalElection.isPresent()) {
            Election election = optionalElection.get();
            List<Lista> lists = listaRepository.findAllByElectionId(electionId);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = null;
            try {
                json = objectMapper.writeValueAsString(lists);
            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return json;
        }
        return "Uspjesno izvrsena ruta";
    }




    @PostMapping("/{electionId}/add-candidates")
    public String addCandidates(@PathVariable Long electionId, @RequestBody List<Kandidat> candidates) {
        return "Nista";
    }
}
