package ba.nwt.electionmanagement.services;

import ba.nwt.electionmanagement.entities.Candidate;
import ba.nwt.electionmanagement.entities.Election;
import ba.nwt.electionmanagement.entities.Lista;
import ba.nwt.electionmanagement.entities.PollingStation;
import ba.nwt.electionmanagement.exception.ErrorDetails;
import ba.nwt.electionmanagement.repositories.CandidateRepository;
import ba.nwt.electionmanagement.repositories.ElectionRepository;
import ba.nwt.electionmanagement.repositories.ListaRepository;
import ba.nwt.electionmanagement.repositories.PollingStationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ElectionService {

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PollingStationRepository pollingStationRepository;

    private ResponseEntity<String> checkElectionExists(Long electionId) {
        Optional<Election> optionalElection = electionRepository.findById(electionId);
        if (optionalElection.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"electionId","ELection ID not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        return null;
    }


    public String getElections() {
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
        return json;
    }

    public ResponseEntity<String> createElection(Election election, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("electionId", election.getId());
        List<PollingStation> pollingStations = new ArrayList<>();
        for (PollingStation pollingStation: election.getPollingStations()) {
            Long id = pollingStation.getId();
            Optional<PollingStation> optionalPollingStation = pollingStationRepository.findById(id);
            if (optionalPollingStation.isPresent()) {
                pollingStations.add(optionalPollingStation.get());
            }
            else {
                ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"pollingStationId","Polling Station ID not found");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
            }
        }
        for (PollingStation pollingStation: pollingStations) {
            pollingStation.getElections().add(election);
            pollingStationRepository.save(pollingStation);
        }
        electionRepository.save(election);
        return ResponseEntity.ok("Successfully created elections " + election.getId());
    }

    public ResponseEntity<String> addLists(Long electionId, List<Lista> liste) {
        Optional<Election> optionalElection = electionRepository.findById(electionId);
        if (optionalElection.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"electionId","ELection ID not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        Election election = optionalElection.get();
        for (Lista lista : liste) {
            lista.setElection(election);
            listaRepository.save(lista);
        }
        return ResponseEntity.ok("Lists added successfully to election " + election.getId());
    }

    public ResponseEntity<String> getListsForElections(Long electionId) {
        Optional<Election> optionalElection = electionRepository.findById(electionId);
        if (optionalElection.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"electionId","ELection ID not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
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

    public ResponseEntity<String> addCandidates(Long electionId, Long listId, List<Candidate> candidates) {
        Optional<Election> optionalElection = electionRepository.findById(electionId);
        if (optionalElection.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"electionId","ELection ID not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        Optional<Lista> optionalLista = listaRepository.findById(listId);
        if (optionalLista.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"listId","List ID not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        Lista list = optionalLista.get();
        for (Candidate candidate: candidates) {
            candidate.setLista(list);
            candidateRepository.save(candidate);
        }
        return ResponseEntity.ok("Candidates added successfully to list " + list.getId());
    }

    public ResponseEntity<String> getCandidates(Long electionId, Long listId) {
        Optional<Election> optionalElection = electionRepository.findById(electionId);
        if (optionalElection.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"electionId","ELection ID not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }

        Optional<Lista> optionalLista = listaRepository.findById(listId);
        if (optionalLista.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"listId","List ID not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }

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

    public ResponseEntity<String> getPollingStations(Long electionId) {
        ResponseEntity<String> responseEntity = checkElectionExists(electionId);
        if (responseEntity != null) return responseEntity;



        return null;
    }

    public ResponseEntity<String> addElectionToPollingStations(Long electionId, List<Long> pollingStationIds) {
        Optional<Election> optionalElection = electionRepository.findById(electionId);
        if (optionalElection.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"electionId","Election ID not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        Election election = optionalElection.get();
        List<PollingStation> pollingStations = pollingStationRepository.findAllById(pollingStationIds);
        if (pollingStations.size() != pollingStationIds.size()) {
            Set<Long> notFoundIds = new HashSet<>(pollingStationIds);
            for (PollingStation ps: pollingStations) {
                notFoundIds.remove(ps.getId());
            }
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"pollingStationIds","Polling Station IDs not found: " + notFoundIds);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());
        }
        election.getPollingStations().addAll(pollingStations);
        for (PollingStation pollingStation : pollingStations) {
            pollingStation.getElections().add(election);
        }
        electionRepository.save(election);
        return ResponseEntity.ok("Election added to polling stations successfully.");
    }
}
