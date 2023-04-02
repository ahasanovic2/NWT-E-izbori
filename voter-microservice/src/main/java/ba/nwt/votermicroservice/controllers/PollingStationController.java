package ba.nwt.votermicroservice.controllers;


import ba.nwt.votermicroservice.interfaces.PollingStationRepository;
import ba.nwt.votermicroservice.interfaces.*;
import ba.nwt.votermicroservice.votermanagement.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/polling-stations")
public class PollingStationController {

    private static final AtomicLong counter = new AtomicLong();
    @Autowired
    private PollingStationRepository pollingStationRepository;

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private ListaRepository listaRepository;

    @GetMapping("")
    public String getPollingStations(){
        List<PollingStation> pollingStations = pollingStationRepository.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(pollingStations);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }


    @GetMapping("/{pollingStationId}/voters")
    public ResponseEntity<String> getVotersByPollingStation(@PathVariable Long pollingStationId) {
        Optional<PollingStation> optionalPollingStation = pollingStationRepository.findById(pollingStationId);
        if (optionalPollingStation.isPresent()) {
            PollingStation pollingStation = optionalPollingStation.get();
            List<Voter> lists = voterRepository.findAllByPollingStationId(pollingStationId);
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
        else return new ResponseEntity<>("Biracko mjesto sa zadanim ID-em ne postoji!", HttpStatus.NOT_FOUND);

    }











}
