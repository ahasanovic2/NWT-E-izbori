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
        LocalDateTime pocetak = LocalDateTime.of(2025,5,10,0,0);
        LocalDateTime kraj = LocalDateTime.of(2025,5,15,0,0);

        Candidate candidate1 = new Candidate();
        candidate1.setFirstName("Bakir");
        candidate1.setLastName("Izetbegovic");
        candidate1.setDescription("Ovo je opis za kandidata za bosnjackog predsjednika, Bakira Izetbegovica.");

        Candidate candidate2 = new Candidate();
        candidate2.setFirstName("Denis");
        candidate2.setLastName("Becirovic");
        candidate2.setDescription("Ovo je opis za kandidata za bosnjackog predsjednika, Denisa Becirovica.");

        Candidate candidate3 = new Candidate();
        candidate3.setFirstName("Nermin");
        candidate3.setLastName("Niksic");
        candidate3.setDescription("Ovo je opis za kandidata za federalni parlament, Nermina niksica.");

        Candidate candidate4 = new Candidate();
        candidate4.setFirstName("Sejo");
        candidate4.setLastName("Bajramovic");
        candidate4.setDescription("Ovo je opis za kandidata za federalni parlament, Denisa Becirovica.");

        Candidate candidate5 = new Candidate();
        candidate5.setFirstName("Glupi");
        candidate5.setLastName("Clan");
        candidate5.setDescription("Ovo je opis za kandidata za federalni parlament, Prvi glupi.");

        Candidate candidate6 = new Candidate();
        candidate6.setFirstName("Drugi");
        candidate6.setLastName("Glupi");
        candidate6.setDescription("Ovo je opis za kandidata za federalni parlament, Drugi glupi.");

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

        Result result = new Result();
        result.setElection(election1);
        result.setVoteCount(500);
        result.setPollingStation(pollingStation1);

        Result result1 = new Result();
        result1.setElection(election1);
        result1.setVoteCount(1000);
        result1.setPollingStation(pollingStation2);

        Result result2 = new Result();
        result2.setElection(election2);
        result2.setVoteCount(100);
        result2.setPollingStation(pollingStation1);

        Result result3 = new Result();
        result3.setElection(election2);
        result3.setVoteCount(100);
        result3.setPollingStation(pollingStation2);

        candidateRepository.save(candidate1);
        candidateRepository.save(candidate2);
        candidateRepository.save(candidate3);
        candidateRepository.save(candidate4);
        candidateRepository.save(candidate5);
        candidateRepository.save(candidate6);
        pollingStationRepository.save(pollingStation1);
        pollingStationRepository.save(pollingStation2);
        pollingStationRepository.save(pollingStation3);
        electionRepository.save(election1);
        electionRepository.save(election2);
        resultRepository.save(result1);
        resultRepository.save(result2);
        resultRepository.save(result3);
    }
}
