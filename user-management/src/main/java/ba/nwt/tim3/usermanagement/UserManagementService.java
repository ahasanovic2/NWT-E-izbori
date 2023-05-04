package ba.nwt.tim3.usermanagement;

import ba.nwt.tim3.usermanagement.entities.PollingStation;
import ba.nwt.tim3.usermanagement.entities.User;
import ba.nwt.tim3.usermanagement.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PollingStationRepository pollingStationRepository;


    @Transactional
    public void createRows() {
        /*User user1 = new User();
        user1.setFirstName("Ahmedin");
        user1.setLastName("Hasanovic");
        user1.setEmail("ahmedin@outlook.com");
        user1.setPassword("sifra123");

        User user2 = new User();
        user2.setFirstName("Vedad");
        user2.setLastName("Dervisevic");
        user2.setEmail("vedad@outlook.com");
        user2.setPassword("vedad123");

        User user3 = new User();
        user3.setFirstName("Ema");
        user3.setLastName("Kalmar");
        user3.setEmail("ema@outlook.com");
        user3.setPassword("ema123");

        User user4 = new User();
        user4.setFirstName("Amina");
        user4.setLastName("Sehic");
        user4.setEmail("amina@outlook.com");
        user4.setPassword("amina123");

        User user5 = new User();
        user5.setFirstName("Irfan");
        user5.setLastName("Prazina");
        user5.setEmail("irfan@outlook.com");
        user5.setPassword("irfan123");

        User user6 = new User();
        user6.setFirstName("Meho");
        user6.setLastName("Mehic");
        user6.setEmail("meho@outlook.com");
        user6.setPassword("meho123");

        User user7 = new User();
        user7.setFirstName("Niko");
        user7.setLastName("Nikic");
        user7.setEmail("niko@outlook.com");
        user7.setPassword("niko123");

        User user8 = new User();
        user8.setFirstName("Goran");
        user8.setLastName("Goranic");
        user8.setEmail("goran@outlook.com");
        user8.setPassword("goran123");

        User user9 = new User();
        user9.setFirstName("Mirza");
        user9.setLastName("Mirzic");
        user9.setEmail("mirza@outlook.com");
        user9.setPassword("mirza123");

        User user10 = new User();
        user10.setFirstName("Jasna");
        user10.setLastName("Jasnic");
        user10.setEmail("jasna@outlook.com");
        user10.setPassword("jasna123");

        PollingStation pollingStation1 = new PollingStation();
        pollingStation1.setName("Stanica 1");
        pollingStation1.setAddress("Hotonj bb");
        pollingStation1.setOpcina("Vogosca");
        pollingStation1.setEntitet("FederacijaBiH");
        pollingStation1.setKanton("Sarajevo");
        pollingStation1.addUser(user1);
        pollingStation1.addUser(user2);
        pollingStation1.addUser(user3);

        PollingStation pollingStation2 = new PollingStation();
        pollingStation2.setName("Stanica 2");
        pollingStation2.setAddress("Hamdije bb");
        pollingStation2.setOpcina("Zenica");
        pollingStation2.setEntitet("FederacijaBiH");
        pollingStation2.setKanton("Zenicko Dobojski");
        pollingStation2.addUser(user4);
        pollingStation2.addUser(user5);
        pollingStation2.addUser(user6);

        PollingStation pollingStation3 = new PollingStation();
        pollingStation3.setName("Stanica 3");
        pollingStation3.setAddress("Glavna ulica bb");
        pollingStation3.setOpcina("Banja Luka");
        pollingStation3.setEntitet("RepublikaSrpska");
        pollingStation3.addUser(user7);
        pollingStation3.addUser(user8);
        pollingStation3.addUser(user9);
        pollingStation3.addUser(user10);

        user1.setPollingStation(pollingStation1);
        user2.setPollingStation(pollingStation1);
        user3.setPollingStation(pollingStation1);
        user4.setPollingStation(pollingStation2);
        user5.setPollingStation(pollingStation2);
        user6.setPollingStation(pollingStation2);
        user7.setPollingStation(pollingStation3);
        user8.setPollingStation(pollingStation3);
        user9.setPollingStation(pollingStation3);
        user10.setPollingStation(pollingStation3);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
        userRepository.save(user7);
        userRepository.save(user8);
        userRepository.save(user9);
        userRepository.save(user10);

        pollingStationRepository.save(pollingStation1);
        pollingStationRepository.save(pollingStation2);
        pollingStationRepository.save(pollingStation3);*/
    }
}
