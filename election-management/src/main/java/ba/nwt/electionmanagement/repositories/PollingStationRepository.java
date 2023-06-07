package ba.nwt.electionmanagement.repositories;

import ba.nwt.electionmanagement.entities.PollingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PollingStationRepository extends JpaRepository<PollingStation, Integer> {
    Optional<PollingStation> findByName(String name);
}
