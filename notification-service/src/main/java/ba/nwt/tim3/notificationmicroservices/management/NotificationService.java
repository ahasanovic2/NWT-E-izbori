package ba.nwt.tim3.notificationmicroservices.management;


import ba.nwt.tim3.notificationmicroservices.management.models.Notification;
import ba.nwt.tim3.notificationmicroservices.management.repositories.NotificationRepository;
import ba.nwt.tim3.notificationmicroservices.management.requests.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public Notification findNotificationById(Long id){
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        if(notificationOptional.isPresent()){
            return notificationOptional.get();
        }
        return null;
    }
    public ArrayList<Notification> findAll(){
        return (ArrayList<Notification>) notificationRepository.findAll();
    }

    public Notification saveNotification(NotificationRequest notificationRequest){
        Notification notification = new Notification(notificationRequest.getType());
        return notificationRepository.save(notification);
    }


}