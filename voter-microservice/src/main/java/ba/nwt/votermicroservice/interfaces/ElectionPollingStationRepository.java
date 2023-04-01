package ba.nwt.votermicroservice.interfaces;

import ba.nwt.votermicroservice.votermanagement.models.ElectionPollingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectionPollingStationRepository extends JpaRepository<ElectionPollingStation, Long> {
}
