package ba.nwt.votermicroservice.services;

import ba.nwt.votermicroservice.exception.ErrorDetails;
import ba.nwt.votermicroservice.grpc.GrpcClient;
import ba.nwt.votermicroservice.models.PollingStation;
import ba.nwt.votermicroservice.models.Voter;
import ba.nwt.votermicroservice.repositories.ElectionRepository;
import ba.nwt.votermicroservice.repositories.ListaRepository;
import ba.nwt.votermicroservice.repositories.PollingStationRepository;
import ba.nwt.votermicroservice.repositories.VoterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    private RestTemplate restTemplate;

    private GrpcClient grpcClient;

    public PollingStationService() {
        grpcClient = GrpcClient.get();
    }

    public String getPollingStations(HttpServletRequest request) {
        ResponseEntity<Integer> userId = getUserId(request);
        List<PollingStation> pollingStations = pollingStationRepository.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(pollingStations);
            grpcClient.log(userId.getBody(), "Voting service","Get Polling stations", "Success");
            return json;
        } catch (JsonProcessingException e) {
            grpcClient.log(userId.getBody(), "Voting service","Get Polling stations", "Fail");
            e.printStackTrace();
            return null;
        }
    }

    public ResponseEntity<String> getVotersByPollingStation(Long pollingStationId, HttpServletRequest request) {
        Optional<PollingStation> optionalPollingStation = pollingStationRepository.findById(pollingStationId);
        ResponseEntity<Integer> userId = getUserId(request);
        if (optionalPollingStation.isPresent()) {
            PollingStation pollingStation = optionalPollingStation.get();
            List<Voter> lists = voterRepository.findAllByPollingStationId(pollingStationId);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = null;
            try {
                json = objectMapper.writeValueAsString(lists);
                grpcClient.log(userId.getBody(), "Voting service","Get voters by polling station", "Success");
                return ResponseEntity.ok(json);
            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
                grpcClient.log(userId.getBody(), "Voting service","Get voters by polling station", "Fail");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
            }
        }
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now().toString(),"pollingStationId","Pollingstation ID not found");
        grpcClient.log(userId.getBody(), "Voting service","Get voters by polling station", "Fail");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails.toString());

    }

    public ResponseEntity<Integer> getUserId(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // Skip past "Bearer "
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<Integer> korisnikId = restTemplate.exchange("http://auth-service/users/id", HttpMethod.GET, entity, Integer.class);
        return korisnikId;
    }
}
