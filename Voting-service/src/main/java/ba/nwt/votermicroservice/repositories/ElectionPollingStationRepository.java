package ba.nwt.votermicroservice.repositories;

import ba.nwt.votermicroservice.models.ElectionPollingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectionPollingStationRepository extends JpaRepository<ElectionPollingStation, Long> {
}
