package ba.nwt.tim3.usermanagement.services;

import ba.nwt.tim3.usermanagement.entities.PollingStation;
import ba.nwt.tim3.usermanagement.entities.User;
import ba.nwt.tim3.usermanagement.repositories.PollingStationRepository;
import ba.nwt.tim3.usermanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PollingStationRepository pollingStationRepository;

    public String getUsers() {
        List<User> users = userRepository.findAll();
        String json = null;
        try {
            StringBuilder sb = new StringBuilder("[");
            for (User user : users) {
                sb.append(user.toString()).append(",");
            }
            if (users.size() > 0) {
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

    public String addUser(User user) {
        userRepository.save(user);
        return "Added user " + user.getId();
    }

    public ResponseEntity<String> setPollingStation(Long userId, Long pollingStationId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<PollingStation> optionalPollingStation = pollingStationRepository.findById(pollingStationId);

        if (optionalUser.isEmpty() || optionalPollingStation.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User or Polling station not found");

        User user = optionalUser.get();
        PollingStation pollingStation = optionalPollingStation.get();

        user.setPollingStation(pollingStation);
        pollingStation.addUser(user);

        userRepository.save(user);
        pollingStationRepository.save(pollingStation);

        return ResponseEntity.ok("Added polling station " + pollingStation.getId() + " to user " + user.getId());

    }
}
