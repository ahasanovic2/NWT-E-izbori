package ba.nwt.tim3.authservice.service;

import ba.nwt.tim3.authservice.exception.ErrorDetails;
import ba.nwt.tim3.authservice.grpc.GrpcClient;
import ba.nwt.tim3.authservice.pollingstation.PollingStation;
import ba.nwt.tim3.authservice.pollingstation.PollingStationRepository;
import ba.nwt.tim3.authservice.user.User;
import ba.nwt.tim3.authservice.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PollingStationService {

    private final PollingStationRepository pollingStationRepository;

    private final UserRepository userRepository;

    private final UserService userService;

    private GrpcClient grpcClient;

    public String getPollingStations() {
        List<PollingStation> pollingStations = pollingStationRepository.findAll();
        String json;
        try {
            StringBuilder sb = new StringBuilder("[");
            for (PollingStation pollingStation : pollingStations) {
                sb.append(pollingStation.toString()).append(",");
            }
            if (pollingStations.size() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("]");
            json = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error serializing elections to JSON: " + e.getMessage());
            return null;
        }
        System.out.println("Serialized JSON: " + json);
        return json;
    }


    public String addPollingStation(Integer userId, PollingStation pollingStation) {
        grpcClient = GrpcClient.get();
        pollingStationRepository.save(pollingStation);
        grpcClient.log(userId,"AuthService","createPS","Success");
        return "Successfully created polling station " + pollingStation.getId();
    }

    public ResponseEntity getPollingStationForUser() {
        Integer userId = userService.getUserIdFromAuthentication();

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDetails(LocalDateTime.now(),"user","User ID not present in database"));
        }
        User user = optionalUser.get();
        PollingStation pollingStation = user.getPollingStation();
        return ResponseEntity.ok(pollingStation);
    }
}
