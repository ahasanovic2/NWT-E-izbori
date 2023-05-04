package ba.nwt.electionmanagement;

import ba.nwt.electionmanagement.repositories.*;
import ba.nwt.electionmanagement.entities.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ElectionManagementService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private PollingStationRepository pollingStationRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void createRows() {
        LocalDateTime pocetak = LocalDateTime.of(2023,5,10,0,0);
        LocalDateTime kraj = LocalDateTime.of(2023,5,15,0,0);

        Election election1 = new Election();
        election1.setName("Izbori za bosnjackog predsjednika");
        election1.setStatus("Active");
        election1.setDescription("Ovo je opis za izbore za bosnjackog clana predsjednistva. Na tim izborima mogu glasati svi kandidati koji zive u federaciji.");
        election1.setStartTime(pocetak);
        election1.setEndTime(kraj);

        Election election2 = new Election();
        election2.setName("Izbori za hrvatskog predsjednika");
        election2.setStatus("Active");
        election2.setDescription("Ovo je opis za izbore za hrvatskog clana predsjednistva. Na tim izborima mogu glasati svi kandidati koji zive u federaciji.");
        election2.setStartTime(pocetak);
        election2.setEndTime(kraj);

        Election election3 = new Election();
        election3.setName("Izbori za kantonalnu skupstinu kantona sarajevo");
        election3.setStatus("Active");
        election3.setDescription("Ovo je opis za izbore za kantonalnu skupstinu. Na tim izborima mogu glasati svi kandidati koji zive u Kantonu Sarajevo.");
        election3.setStartTime(pocetak);
        election3.setEndTime(kraj);




    }
}
