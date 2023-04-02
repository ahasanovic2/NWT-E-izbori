package ba.nwt.electionmanagement.controllers;

import ba.nwt.electionmanagement.repositories.CandidateRepository;
import ba.nwt.electionmanagement.repositories.ElectionRepository;
import ba.nwt.electionmanagement.repositories.ListaRepository;
import ba.nwt.electionmanagement.entities.Election;
import ba.nwt.electionmanagement.entities.Candidate;
import ba.nwt.electionmanagement.entities.Lista;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping("")
    public ResponseEntity<String> getElections() {
        List<Election> elections = electionRepository.findAll();
        String json = null;
        try {
            StringBuilder sb = new StringBuilder("[");
            for (Election election : elections) {
                sb.append(election.toString()).append(",");
            }
            if (elections.size() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("]");
            json = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error serializing elections to JSON: " + e.getMessage());
        }
        System.out.println("Serialized JSON: " + json);
        return ResponseEntity.ok(json);
    }



    @PostMapping("/create")
    public String createElection(@Valid @RequestBody Election election, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("electionId", election.getId());
        electionRepository.save(election);
        return "redirect:/elections/" + election.getId() + "/add-lists";
    }

    @PostMapping("/{electionId}/add-lists")
    public ResponseEntity<String> addLists(@PathVariable Long electionId, @Valid @RequestBody List<Lista> liste) {
        Optional<Election> optionalElection = electionRepository.findById(electionId);
        if (!optionalElection.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Election ID not found");
        }
        Election election = optionalElection.get();
        for (Lista lista : liste) {
            lista.setElection(election);
            listaRepository.save(lista);
        }
        return ResponseEntity.ok("Lists added successfully");
    }


    @GetMapping("/{electionId}/lists")
    public ResponseEntity<String> getListsForElections(@PathVariable Long electionId) {
        Optional<Election> optionalElection = electionRepository.findById(electionId);
        if (!optionalElection.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Election ID not found");

        List<Lista> lists = listaRepository.findAllByElectionId(electionId);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(lists);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(json);
    }


    @PostMapping("/{electionId}/lists/{listId}/add-candidates")
    public ResponseEntity<String> addCandidates(@PathVariable Long electionId, @PathVariable Long listId, @Valid @RequestBody List<Candidate> candidates) {
        Optional<Election> optionalElection = electionRepository.findById(electionId);
        if (!optionalElection.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Election ID not found");
        Optional<Lista> optionalLista = listaRepository.findById(listId);
        if (!optionalLista.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("List ID not found");
        Lista list = optionalLista.get();
        for (Candidate candidate: candidates) {
            candidate.setLista(list);
            candidateRepository.save(candidate);
        }
        return ResponseEntity.ok("Lists added successfully");
    }

    @GetMapping("/{electionId}/lists/{listId}/candidates")
    public ResponseEntity<String> getCandidates(@PathVariable Long electionId, @PathVariable Long listId) {
        Optional<Election> optionalElection = electionRepository.findById(electionId);
        if (!optionalElection.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Election ID not found");

        Optional<Lista> optionalLista = listaRepository.findById(listId);
        if (!optionalLista.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("List ID not found");

        List<Candidate> candidates = candidateRepository.findAllByListaId(listId);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(candidates);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(json);
    }
}
