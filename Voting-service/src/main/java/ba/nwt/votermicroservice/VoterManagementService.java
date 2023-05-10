package ba.nwt.votermicroservice;

import ba.nwt.votermicroservice.repositories.*;
import ba.nwt.votermicroservice.models.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VoterManagementService {

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private VoterRepository voterRepository;
    @Autowired
    private PollingStationRepository pollingStationRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private ListaRepository listaRepository;
    @Autowired
    private ElectionPollingStationRepository electionPollingStationRepository;

    private Voter createVoter(String firstName, String lastName, PollingStation pollingStation) {
        Voter voter = new Voter();
        voter.setFirstName(firstName);
        voter.setLastName(lastName);
        voter.setPollingStation(pollingStation);
        return voter;
    }
    @Transactional
    public void createRows(){/*
        LocalDateTime pocetak = LocalDateTime.of(2025,5,10,0,0);
        LocalDateTime kraj = LocalDateTime.of(2025,5,15,0,0);

        Election election1 = new Election();
        election1.setName("Izbori za bosnjackog predsjednikaa");
        election1.setStatus("Active");
        election1.setDescription("Ovo je opis za izbore za bosnjackog clana predsjednistva. Na tim izborima mogu glasati svi kandidati koji zive u federaciji.");
        election1.setStartTime(pocetak);
        election1.setEndTime(kraj);

        Election election2 = new Election();
        election2.setName("Izbori za narodnu skupstinu rs");
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
        candidate3.setDescription("Ovo je opis za kandidata za nsrs, Nermina niksica.");
        candidate3.setLista(lista2);

        Candidate candidate4 = new Candidate();
        candidate4.setFirstName("Sejo");
        candidate4.setLastName("Bajramovic");
        candidate4.setDescription("Ovo je opis za kandidata za nsrs, Denisa Becirovica.");
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
        candidate5.setDescription("Ovo je opis za kandidata za nsrs, Prvi glupi.");
        candidate5.setLista(lista3);

        Candidate candidate6 = new Candidate();
        candidate6.setFirstName("Drugi");
        candidate6.setLastName("Glupi");
        candidate6.setDescription("Ovo je opis za kandidata za nsrs, Drugi glupi.");
        candidate6.setLista(lista3);

        lista3.addCandidates(candidate5);
        lista3.addCandidates(candidate6);


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

        Voter voter1 = createVoter("Ahmedin","Hasanovic",pollingStation1);
        Voter voter2 = createVoter("Vedad","Dervisevic",pollingStation1);
        Voter voter3 = createVoter("Ema","Kalmar",pollingStation2);
        Voter voter4 = createVoter("Amina","Sehic",pollingStation2);
        Voter voter5 = createVoter("Rudolf","Turkovic",pollingStation3);
        Voter voter6 = createVoter("Neki","Srbin",pollingStation3);

        Vote vote1 = createVote(voter1, election1, candidate2, LocalDateTime.now(), null);
        Vote vote2 = createVote(voter2, election1, candidate1, LocalDateTime.now(), null);
        Vote vote3 = createVote(voter3, election1, candidate1, LocalDateTime.now(), null);
        Vote vote4 = createVote(voter4, election1, candidate2, LocalDateTime.now(), null);

        Vote vote5 = createVote(voter5, election2, candidate3, LocalDateTime.now(),null);
        Vote vote6 = createVote(voter6, election2, candidate4, LocalDateTime.now(),null);

        voterRepository.save(voter1);
        voterRepository.save(voter2);
        voterRepository.save(voter3);
        voterRepository.save(voter4);
        voterRepository.save(voter5);
        voterRepository.save(voter6);

        voteRepository.save(vote1);
        voteRepository.save(vote2);
        voteRepository.save(vote3);
        voteRepository.save(vote4);
        voteRepository.save(vote5);
        voteRepository.save(vote6);

        pollingStationRepository.save(pollingStation1);
        pollingStationRepository.save(pollingStation2);
        pollingStationRepository.save(pollingStation3);

        */
    }
    public Vote createVote(Voter voter, Election election, Candidate candidate, LocalDateTime timestamp, Lista lista) {
        Vote vote = new Vote();
        vote.setVoter(voter);
        vote.setElection(election);
        vote.setCandidate(candidate);
        vote.setTimestamp(timestamp);
        vote.setLista(lista);
        return vote;
    }

}
