package ba.nwt.votermicroservice.controllers;


import ba.nwt.votermicroservice.services.PollingStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/polling-stations")
public class PollingStationController {
    @Autowired
    private PollingStationService pollingStationService = new PollingStationService();

    @GetMapping("")
    public ResponseEntity<String> getPollingStations(){
        return ResponseEntity.ok(pollingStationService.getPollingStations());

    }


    @GetMapping("/{pollingStationId}/voters")
    public ResponseEntity<String> getVotersByPollingStation(@PathVariable Long pollingStationId) {
        return pollingStationService.getVotersByPollingStation(pollingStationId);


    }











}
