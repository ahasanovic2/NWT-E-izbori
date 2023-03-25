package ba.nwt.votermicroservice;

import ba.nwt.votermicroservice.votermanagement.models.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<Vote> glasovi = new ArrayList<>();

        voter.setFirst_name("Vedad");
        voter.setLast_name("Dervisevic");
        glasovi.add(vote);
        vote.setVoter(voter);
        voter.setVotes(glasovi);

        voterRepository.save(voter);

        voteRepository.save(vote);


    }

}
