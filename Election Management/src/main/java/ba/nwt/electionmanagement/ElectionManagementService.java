package ba.nwt.electionmanagement;

import ba.nwt.electionmanagement.repositories.*;
import ba.nwt.electionmanagement.entities.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElectionManagementService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private PollingStationRepository pollingStationRepository;

    @Autowired
    private UserRepository userRepository;


    private User kreirajKorisnika(String ime, String prezime) {
        User user = new User();
        user.setFirst_name(ime);
        user.setLast_name(prezime);
        return user;
    }

    @Transactional
    public void createRows() {
        /*userRepository.deleteAll();
        candidateRepository.deleteAll();
        listaRepository.deleteAll();
        pollingStationRepository.deleteAll();
        electionRepository.deleteAll();


        User user1 = kreirajKorisnika("Ahmedin", "Hasanović");
        User user2 = kreirajKorisnika("Vedad", "Dervisevic");
        User user3 = kreirajKorisnika("Ema", "Kalmar");
        User user4 = kreirajKorisnika("Amina", "Šehić");



        PollingStation pollingStation = new PollingStation();
        PollingStation pollingStation2 = new PollingStation();
        Election election1 = new Election();
        pollingStation.setElection(election1);
        pollingStation2.setElection(election1);
        pollingStation.setName("Hotonj 1");
        pollingStation.addVoter(user1);
        pollingStation.addVoter(user2);
        election1.addPollingStation(pollingStation);

        pollingStation2.setName("Vogosca 2");
        pollingStation.addVoter(user3);
        pollingStation.addVoter(user4);
        election1.addPollingStation(pollingStation2);

        election1.setName("Izbori za clana predsjednistva");

        Kandidat kandidat = new Kandidat();
        kandidat.setFirstName("Prvoime");
        kandidat.setLastName("Prvoprezime");
        Kandidat kandidat2 = new Kandidat();
        kandidat2.setFirstName("Drugoime");
        kandidat2.setLastName("Drugoprezime");
        Kandidat kandidat3 = new Kandidat();
        kandidat3.setFirstName("TreceIme");
        kandidat3.setLastName("TrecePrezime");
        Kandidat kandidat4 = new Kandidat();
        kandidat4.setLastName("Cetvrtoprezime");
        kandidat4.setFirstName("CetvrtoIme");

        Lista list1 = new Lista();
        list1.addCandidates(kandidat);
        list1.addCandidates(kandidat2);
        Lista list2 = new Lista();
        list2.addCandidates(kandidat3);
        list2.addCandidates(kandidat4);

        election1.addLista(list1);
        election1.addLista(list2);
        user1.setPollingStation(pollingStation);
        user2.setPollingStation(pollingStation);
        user3.setPollingStation(pollingStation2);
        user4.setPollingStation(pollingStation2);

        candidateRepository.save(kandidat);
        candidateRepository.save(kandidat2);
        candidateRepository.save(kandidat3);
        candidateRepository.save(kandidat4);

        listaRepository.save(list1);
        listaRepository.save(list2);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);

        electionRepository.save(election1);
        pollingStationRepository.save(pollingStation);
        pollingStationRepository.save(pollingStation2);*/


    }
}
