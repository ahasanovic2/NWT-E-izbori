package ba.nwt.tim3.usermanagement.services;

import ba.nwt.tim3.usermanagement.entities.PollingStation;
import ba.nwt.tim3.usermanagement.repositories.PollingStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollingStationService {

    @Autowired
    private PollingStationRepository pollingStationRepository;

    public String getPollingStations() {
        List<PollingStation> pollingStations = pollingStationRepository.findAll();
        String json = null;
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
        }
        System.out.println("Serialized JSON: " + json);
        return json;
    }


    public String addPollingStation(PollingStation pollingStation) {
        pollingStationRepository.save(pollingStation);
        return "Successfully created polling station " + pollingStation.getId();
    }
}
