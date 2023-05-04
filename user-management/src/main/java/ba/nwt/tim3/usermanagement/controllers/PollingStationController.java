package ba.nwt.tim3.usermanagement.controllers;

import ba.nwt.tim3.usermanagement.entities.PollingStation;
import ba.nwt.tim3.usermanagement.repositories.PollingStationRepository;
import ba.nwt.tim3.usermanagement.services.PollingStationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pollingStations")
public class PollingStationController {
    @Autowired
    private PollingStationService pollingStationService;

    @GetMapping("")
    public ResponseEntity<String> getPollingStations() {
        return ResponseEntity.ok(pollingStationService.getPollingStations());
    }

    @PostMapping("/create")
    public ResponseEntity<String> addPollingStation(@Valid @RequestBody PollingStation pollingStation) {
        return ResponseEntity.ok(pollingStationService.addPollingStation(pollingStation));
    }

}
