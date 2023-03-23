package ba.nwt.tim3;

import ba.nwt.tim3.models.Candidate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private PollingStationRepository pollingStationRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Transactional
    public void createRows() {

        Candidate candidate = new Candidate();
        candidate.setName("Ema");
        candidateRepository.save(candidate);

    }
}
