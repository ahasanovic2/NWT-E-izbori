package ba.nwt.tim3.notificationmicroservices.management.controllers;


import ba.nwt.tim3.notificationmicroservices.management.NotificationService;
import ba.nwt.tim3.notificationmicroservices.management.models.ErrorMessage;
import ba.nwt.tim3.notificationmicroservices.management.models.Notification;
import ba.nwt.tim3.notificationmicroservices.management.requests.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/notifications")

public class NotificationController {
    @Autowired
    private NotificationService notificationService = new NotificationService();

    @GetMapping("")
    public ResponseEntity<?> getNotifications() {
        return ResponseEntity.ok(notificationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNotificationById(@PathVariable Long id) {
        Notification notification = notificationService.findNotificationById(id);
        if (notification == null) {
            return ResponseEntity.badRequest().body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), String.format("Notification with id: %s does not exist", id)));
        }
        return ResponseEntity.ok().body(notification);
    }
    @PostMapping("/")
    public ResponseEntity<?> addNotification(@RequestBody NotificationRequest notificationRequest) {
        Notification notification = notificationService.saveNotification(notificationRequest);
        return ResponseEntity.created(URI.create(String.format("/api/categories/%s", notification.getId()))).body(notification);
    }

}