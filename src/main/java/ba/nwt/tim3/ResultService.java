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

        /*candidateRepository.deleteAll();
        electionRepository.deleteAll();
        listRepository.deleteAll();
        pollingStationRepository.deleteAll();
        resultRepository.deleteAll();*/

        Candidate candidate = new Candidate();
        candidate.setName("Ema");
        //candidateRepository.save(candidate);

        Candidate candidate1 = new Candidate();
        candidate1.setName("Ahmedin");
        //candidateRepository.save(candidate1);

        Candidate candidate2 = new Candidate();
        candidate2.setName("Vedad");
        candidateRepository.save(candidate2);

        PollingStation pollingStation = new PollingStation();
        pollingStation.setName("Polling Station 1");
        pollingStation.setAdress("Porodice Ribar");
        pollingStationRepository.save(pollingStation);

        PollingStation pollingStation1 = new PollingStation();
        pollingStation1.setName("Polling Station 2");
        pollingStation1.setAdress("Aleja lipa");
        pollingStationRepository.save(pollingStation1);

        java.util.List<Candidate> candidateList = new ArrayList<>();
        candidateList.add(candidate);
        candidateList.add(candidate1);

        List list = new List();
        list.setCandidateList(candidateList);
        list.setName("Lista");
        java.util.List<List> lists = new ArrayList<>();
        lists.add(list);

        candidate.setList(list);
        candidate1.setList(list);

        candidateRepository.save(candidate);
        candidateRepository.save(candidate1);

        listRepository.save(list);

        Result r1 = new Result();
        r1.setCandidate(candidate);
        r1.setVote_count(3000);
        resultRepository.save(r1);


        PollingStation pollingStation2 = new PollingStation();
        pollingStation2.setName("Polling Station 3");
        pollingStation2.setAdress("Dzemala Bijedica");
        pollingStation2.setResult(r1);
        pollingStationRepository.save(pollingStation2);


        Election election = new Election();
        election.setName("Election1");
        election.setStart_time(LocalDateTime.of(2023,04,10,15,00));
        election.setEnd_time(LocalDateTime.of(2023,04,11,18,00));
        electionRepository.save(election);


        List list1 = new List();
        list1.setName("Lista 1");
        list1.setResult(r1);
        listRepository.save(list1);


        Result r2 = new Result();
        r2.setVote_count(5000);
        resultRepository.save(r2);


        Candidate candidate3 = new Candidate();
        candidate3.setName("Tea");
        candidate3.setResult(r2);
        candidateRepository.save(candidate3);


        Result result = new Result();
        result.setCandidate(candidate);
        result.setElection(election);
        result.setList(list);
        result.setPollingStation(pollingStation);
        result.setVote_count(1200);
        resultRepository.save(result);

    }
}
