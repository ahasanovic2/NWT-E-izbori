package ba.nwt.electionmanagement;

import ba.nwt.electionmanagement.interfaces.*;
import ba.nwt.electionmanagement.models.*;
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
//        Lista lista = new Lista();
//        Election elections = new Election();
//        User user = new User();
//        PollingStation pollingStation = new PollingStation();
//
//
//        ArrayList<Kandidat> lista_kandidata = new ArrayList<>();
//        Kandidat candidate = new Kandidat();
//        candidate.setFirst_name("Ahmedin");
//        candidate.setLast_name("Hasanovic");
//        candidate.setDescription("Najbolji izbor za predsjednika");
//        candidate.setLista(lista);
//        lista_kandidata.add(candidate);
//        candidateRepository.save(candidate);
//
//        lista.setCandidates(lista_kandidata);
//        lista.setName("Nezavisna");
//        lista.setElection(elections);
//
//        listaRepository.save(lista);
//        electionRepository.save(elections);
        userRepository.deleteAll();
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


        user1.setPollingStation(pollingStation);
        user2.setPollingStation(pollingStation);
        user3.setPollingStation(pollingStation2);
        user4.setPollingStation(pollingStation2);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);

        electionRepository.save(election1);
        pollingStationRepository.save(pollingStation);
        pollingStationRepository.save(pollingStation2);


    }
}
