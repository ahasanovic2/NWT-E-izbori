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
    public void createRows() {
    }

}
