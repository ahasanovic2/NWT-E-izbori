package ba.nwt.tim3;

import ba.nwt.tim3.interfaces.*;
import ba.nwt.tim3.models.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

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

    }
}
