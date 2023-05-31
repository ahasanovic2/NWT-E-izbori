package ba.nwt.tim3.authservice.controller;

import ba.nwt.tim3.authservice.grpc.GrpcClient;
import ba.nwt.tim3.authservice.pollingstation.PollingStation;
import ba.nwt.tim3.authservice.service.PollingStationService;
import ba.nwt.tim3.authservice.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pollingStations")
public class PollingStationController {

    @Autowired
    private PollingStationService pollingStationService;

    @GetMapping("")
    public ResponseEntity<String> getPollingStations() {
        String povrat = pollingStationService.getPollingStations();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        Integer userId = userDetails.getId();
        if (povrat == null) {
            GrpcClient.log(userId,"AuthService","getPS","Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error serializing to JSON");
        }
        else {
            GrpcClient.log(userId,"AuthService","getPS","Success");
            return ResponseEntity.status(HttpStatus.OK).body(povrat);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> addPollingStation(@Valid @RequestBody PollingStation pollingStation) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        Integer userId = userDetails.getId();
        return ResponseEntity.ok(pollingStationService.addPollingStation(userId, pollingStation));
    }
}
