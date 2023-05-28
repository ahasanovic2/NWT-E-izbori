package ba.nwt.votermicroservice.controllers;


import ba.nwt.votermicroservice.services.PollingStationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/polling-stations")
public class PollingStationController {
    @Autowired
    private PollingStationService pollingStationService = new PollingStationService();

    @GetMapping("")
    public ResponseEntity<String> getPollingStations(HttpServletRequest request){
        return ResponseEntity.ok(pollingStationService.getPollingStations(request));

    }


    @GetMapping("/{pollingStationId}/voters")
    public ResponseEntity<String> getVotersByPollingStation(@PathVariable Long pollingStationId, HttpServletRequest request) {
        return pollingStationService.getVotersByPollingStation(pollingStationId, request);


    }











}
