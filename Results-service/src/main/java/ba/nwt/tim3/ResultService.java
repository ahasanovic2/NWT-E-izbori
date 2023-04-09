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

        Candidate candidate3 = new Candidate();
        candidate3.setName("Tea");
        candidateRepository.save(candidate3);

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
        election.setStart_time(LocalDateTime.of(2023,04,10,15,00));
        election.setEnd_time(LocalDateTime.of(2023,04,11,18,00));
        electionRepository.save(election);

        Election election1 = new Election();
        election1.setName("Election2");
        election1.setStart_time(LocalDateTime.of(2023,04,15,15,00));
        election1.setEnd_time(LocalDateTime.of(2023,04,16,18,00));
        electionRepository.save(election1);

        java.util.List<Candidate> candidateList = new ArrayList<>();
        candidateList.add(candidate);
        candidateList.add(candidate1);

        List list = new List();
        list.setName("Lista kandidata 1");
        java.util.List<List> lists = new ArrayList<>();
        lists.add(list);


        candidateRepository.save(candidate);
        candidateRepository.save(candidate1);
        listRepository.save(list);

        Result r1 = new Result();
        r1.setCandidate(candidate);
        r1.setPollingStation(pollingStation);
        r1.setElection(election);
        r1.setVote_count(3000);
        resultRepository.save(r1);

        Result r2 = new Result();
        r2.setList(list);
        r2.setPollingStation(pollingStation1);
        r2.setElection(election1);
        r2.setVote_count(5000);
        resultRepository.save(r2);


        PollingStation pollingStation2 = new PollingStation();
        pollingStation2.setName("Polling Station 3");
        pollingStation2.setAdress("Dzemala Bijedica");
        pollingStationRepository.save(pollingStation2);


        Election election3 = new Election();
        election3.setName("Election1");
        election3.setStart_time(LocalDateTime.of(2023,04,20,15,00));
        election3.setEnd_time(LocalDateTime.of(2023,04,21,18,00));
        electionRepository.save(election3);


        List list1 = new List();
        list1.setName("Lista 1");
        listRepository.save(list1);


        java.util.List<Result> results = new ArrayList<>();
        results.add(r2);


        Result result = new Result();
        result.setCandidate(candidate3);
        result.setElection(election3);
        result.setPollingStation(pollingStation2);
        result.setVote_count(1200);
        resultRepository.save(result);

    }
}
