package ba.nwt.electionmanagement;

import ba.nwt.electionmanagement.models.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
        User user = new User();
        user.setFirst_name("Ahmedin");
        user.setLast_name("Hasanovic");
        userRepository.save(user);
    }
}
