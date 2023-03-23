package ba.nwt.tim3.notificationmicroservices.management;


import ba.nwt.tim3.notificationmicroservices.management.models.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationManagementService {

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public void createRows() {

        notificationRepository.deleteAll();
        userRepository.deleteAll();


        User user1 = kreirajKorisnika("Ahmedin", "Hasanović");
        User user2 = kreirajKorisnika("Vedad", "Dervisevic");
        User user3 = kreirajKorisnika("Ema", "Kalmar");
        User user4 = kreirajKorisnika("Amina", "Šehić");
        Notification not1 =  new Notification();
        Notification not2 =  new Notification();
        Notification not3 =  new Notification();
        Notification not4 =  new Notification();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);

        not1.setUser(user1);
        not2.setUser(user2);
        not3.setUser(user3);
        not4.setUser(user4);

        notificationRepository.save(not1);
        notificationRepository.save(not2);
        notificationRepository.save(not3);
        notificationRepository.save(not4);




    }

    private User kreirajKorisnika(String first_name, String last_name) {
        User user = new User();
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        return user;
    }

}
