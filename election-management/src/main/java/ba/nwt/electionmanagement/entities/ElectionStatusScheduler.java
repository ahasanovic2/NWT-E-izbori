package ba.nwt.electionmanagement.entities;

import ba.nwt.electionmanagement.repositories.ElectionRepository;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ElectionStatusScheduler {
    private final ElectionRepository electionRepository;

    @Scheduled(fixedRate = 60000) // adjust this value to set how often the task should run (60000ms = 1 minute)
    public void updateElectionStatuses() {
        List<Election> elections = electionRepository.findAll();
        for (Election election : elections) {
            updateStatus(election);
        }
    }

    private void updateStatus(Election election) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(election.getStartTime())) {
            election.setStatus("NotStarted");
        } else if (now.isAfter(election.getEndTime())) {
            election.setStatus("Finished");
        } else {
            election.setStatus("Active");
        }
        electionRepository.save(election);
    }
}
