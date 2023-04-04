package ba.nwt.tim3;

import ba.nwt.tim3.interfaces.*;
import ba.nwt.tim3.models.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

        /*candidateRepository.deleteAll();
        electionRepository.deleteAll();
        listRepository.deleteAll();
        pollingStationRepository.deleteAll();
        resultRepository.deleteAll();*/

        Candidate candidate = new Candidate();
        candidate.setName("Ema");
        candidateRepository.save(candidate);

        Candidate candidate1 = new Candidate();
        candidate1.setName("Ahmedin");
        candidateRepository.save(candidate1);

        PollingStation pollingStation = new PollingStation();
        pollingStation.setName("Polling Station 1");
        pollingStation.setAdress("Porodice Ribar");
        pollingStationRepository.save(pollingStation);

        PollingStation pollingStation1 = new PollingStation();
        pollingStation1.setName("Polling Station 2");
        pollingStation1.setAdress("Aleja lipa");
        pollingStationRepository.save(pollingStation1);

        Election election = new Election();
        election.setName("Election1");
        election.setStart_time(LocalDateTime.of(2023,04,04,15,00));
        election.setEnd_time(LocalDateTime.of(2023,04,04,18,00));
        electionRepository.save(election);


        List list = new List();
        list.setName("Lista 1");
        listRepository.save(list);

        Result result = new Result();
        result.setCandidate(candidate);
        result.setElection(election);
        //result.setList(list);
        result.setPollingStation(pollingStation);
        result.setVote_count(1200);
        resultRepository.save(result);

    }
}
