package ba.nwt.tim3.authservice.controller;

import ba.nwt.tim3.authservice.grpc.GrpcClient;
import ba.nwt.tim3.authservice.pollingstation.PollingStation;
import ba.nwt.tim3.authservice.service.PollingStationService;
import ba.nwt.tim3.authservice.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pollingStations")
@RequiredArgsConstructor
public class PollingStationController {

    private final PollingStationService pollingStationService;
    private GrpcClient grpcClient;

    @GetMapping("")
    public ResponseEntity<String> getPollingStations() {
        grpcClient = GrpcClient.get();
        String povrat = pollingStationService.getPollingStations();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        Integer userId = userDetails.getId();
        if (povrat == null) {
            grpcClient.log(userId,"AuthService","getPS","Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error serializing to JSON");
        }
        else {
            grpcClient.log(userId,"AuthService","getPS","Success");
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

    @GetMapping("/user")
    public ResponseEntity getPollingStationForUser() {
        return pollingStationService.getPollingStationForUser();
    }
}
