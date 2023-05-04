package ba.nwt.tim3.usermanagement.controllers;

import ba.nwt.tim3.usermanagement.entities.User;
import ba.nwt.tim3.usermanagement.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<String> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/create")
    public ResponseEntity<String> addUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @PostMapping("/{userId}/pollingStation/{pollingStationId}")
    public ResponseEntity<String> setPollingStation(@PathVariable Long userId, @PathVariable Long pollingStationId) {
        return userService.setPollingStation(userId,pollingStationId);
    }
}
