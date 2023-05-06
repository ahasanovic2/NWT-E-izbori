package ba.nwt.electionmanagement;

import ba.nwt.electionmanagement.repositories.*;
import ba.nwt.electionmanagement.entities.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    @Transactional
    public void createRows() {
        /*LocalDateTime pocetak = LocalDateTime.of(2025,5,10,0,0);
        LocalDateTime kraj = LocalDateTime.of(2025,5,15,0,0);

        Election election1 = new Election();
        election1.setName("Izbori za bosnjackog predsjednika");
        election1.setStatus("Active");
        election1.setDescription("Ovo je opis za izbore za bosnjackog clana predsjednistva. Na tim izborima mogu glasati svi kandidati koji zive u federaciji.");
        election1.setStartTime(pocetak);
        election1.setEndTime(kraj);

        Election election2 = new Election();
        election2.setName("Izbori za federalni parlament");
        election2.setStatus("Active");
        election2.setDescription("Ovo je opis za izbore za hrvatskog clana predsjednistva. Na tim izborima mogu glasati svi kandidati koji zive u federaciji.");
        election2.setStartTime(pocetak);
        election2.setEndTime(kraj);


        Lista lista1 = new Lista();
        lista1.setNezavisna(true);
        lista1.setName("Kandidati za predsjednika");
        lista1.setElection(election1);
        election1.addLista(lista1);

        Candidate candidate1 = new Candidate();
        candidate1.setFirstName("Bakir");
        candidate1.setLastName("Izetbegovic");
        candidate1.setDescription("Ovo je opis za kandidata za bosnjackog predsjednika, Bakira Izetbegovica.");
        candidate1.setLista(lista1);

        Candidate candidate2 = new Candidate();
        candidate2.setFirstName("Denis");
        candidate2.setLastName("Becirovic");
        candidate2.setDescription("Ovo je opis za kandidata za bosnjackog predsjednika, Denisa Becirovica.");
        candidate2.setLista(lista1);

        lista1.addCandidates(candidate1);
        lista1.addCandidates(candidate2);

        Lista lista2 = new Lista();
        lista2.setNezavisna(false);
        lista2.setName("Prva lista za parlament");
        lista2.setElection(election2);
        election2.addLista(lista2);

        Candidate candidate3 = new Candidate();
        candidate3.setFirstName("Nermin");
        candidate3.setLastName("Niksic");
        candidate3.setDescription("Ovo je opis za kandidata za federalni parlament, Nermina niksica.");
        candidate3.setLista(lista2);

        Candidate candidate4 = new Candidate();
        candidate4.setFirstName("Sejo");
        candidate4.setLastName("Bajramovic");
        candidate4.setDescription("Ovo je opis za kandidata za federalni parlament, Denisa Becirovica.");
        candidate4.setLista(lista2);

        lista2.addCandidates(candidate3);
        lista2.addCandidates(candidate4);

        Lista lista3 = new Lista();
        lista3.setNezavisna(false);
        lista3.setName("Druga lista za parlament");
        lista3.setElection(election2);
        election2.addLista(lista3);

        Candidate candidate5 = new Candidate();
        candidate5.setFirstName("Glupi");
        candidate5.setLastName("Clan");
        candidate5.setDescription("Ovo je opis za kandidata za federalni parlament, Prvi glupi.");
        candidate5.setLista(lista3);

        Candidate candidate6 = new Candidate();
        candidate6.setFirstName("Drugi");
        candidate6.setLastName("Glupi");
        candidate6.setDescription("Ovo je opis za kandidata za federalni parlament, Drugi glupi.");
        candidate6.setLista(lista3);

        lista3.addCandidates(candidate5);
        lista3.addCandidates(candidate6);

        PollingStation pollingStation1 = new PollingStation();
        pollingStation1.setName("Stanica 1");
        pollingStation1.setAddress("Hotonj bb");
        pollingStation1.setOpcina("Vogosca");
        pollingStation1.setEntitet("FederacijaBiH");
        pollingStation1.setKanton("Sarajevo");

        PollingStation pollingStation2 = new PollingStation();
        pollingStation2.setName("Stanica 2");
        pollingStation2.setAddress("Hamdije bb");
        pollingStation2.setOpcina("Zenica");
        pollingStation2.setEntitet("FederacijaBiH");
        pollingStation2.setKanton("Zenicko Dobojski");

        PollingStation pollingStation3 = new PollingStation();
        pollingStation3.setName("Stanica 3");
        pollingStation3.setAddress("Glavna ulica bb");
        pollingStation3.setOpcina("Banja Luka");
        pollingStation3.setEntitet("RepublikaSrpska");

        election1.addPollingStation(pollingStation1);
        election1.addPollingStation(pollingStation2);
        election2.addPollingStation(pollingStation1);
        election2.addPollingStation(pollingStation2);

        pollingStation1.addElections(election1);
        pollingStation1.addElections(election2);
        pollingStation2.addElections(election2);
        pollingStation2.addElections(election1);


        electionRepository.save(election1);
        electionRepository.save(election2);
        listaRepository.save(lista1);
        listaRepository.save(lista2);
        listaRepository.save(lista3);
        candidateRepository.save(candidate1);
        candidateRepository.save(candidate2);
        candidateRepository.save(candidate3);
        candidateRepository.save(candidate4);
        candidateRepository.save(candidate5);
        candidateRepository.save(candidate6);
        pollingStationRepository.save(pollingStation1);
        pollingStationRepository.save(pollingStation2);*/


    }
}
