package ba.nwt.votermicroservice.services;

import ba.nwt.votermicroservice.exception.ErrorDetails;
import ba.nwt.votermicroservice.models.PollingStation;
import ba.nwt.votermicroservice.models.Voter;
import ba.nwt.votermicroservice.repositories.ElectionRepository;
import ba.nwt.votermicroservice.repositories.ListaRepository;
import ba.nwt.votermicroservice.repositories.PollingStationRepository;
import ba.nwt.votermicroservice.repositories.VoterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PollingStationService {

    @Autowired
    private PollingStationRepository pollingStationRepository;

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private ListaRepository listaRepository;


    public String getPollingStations() {
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

    public ResponseEntity<String> getVotersByPollingStation(Long pollingStationId) {
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
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"pollingStationId","Pollingstation ID not found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());

    }
}
