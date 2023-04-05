package ba.nwt.votermicroservice;

import ba.nwt.votermicroservice.repositories.*;
import ba.nwt.votermicroservice.models.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Transactional
    public void createRows(){

        Vote vote = new Vote();
        Voter voter = new Voter();
        Election election = new Election();
        election.setDescription("Predsjednicki izbori");



        PollingStation pollingStation = new PollingStation();
        pollingStation.setAdress("Avde Hume");
        pollingStation.setName("Biraliste 1");
        List<Vote> glasovi = new ArrayList<>();
        Candidate candidate = new Candidate();
        candidate.setFirst_name("Ahmedin");
        candidate.setLast_name("Hasanovic");

        Candidate candidate1 = new Candidate();
        candidate1.setFirst_name("Samir");
        candidate1.setLast_name("Memic");
        List<Candidate> kandidatskaLista = new ArrayList<>();
        kandidatskaLista.add(candidate);
        kandidatskaLista.add(candidate1);

        voter.setFirst_name("Vedad");
        voter.setLast_name("Dervisevic");
        Lista lista = new Lista();
        lista.setCandidates(kandidatskaLista);
        lista.setName("Lista za izbora bosnjackog clana");
        List<Lista> liste = new ArrayList<>();
        liste.add(lista);



        election.setLists(liste);
        lista.setElection(election);
        candidate.setLista(lista);
        candidate1.setLista(lista);
        glasovi.add(vote);
        vote.setVoter(voter);
        voter.setVotes(glasovi);
        voter.setPollingStation(pollingStation);
        pollingStationRepository.save(pollingStation);
        listaRepository.save(lista);
        candidateRepository.save(candidate);
        candidateRepository.save(candidate1);
        voterRepository.save(voter);
        voteRepository.save(vote);
        electionRepository.save(election);


    }

}
