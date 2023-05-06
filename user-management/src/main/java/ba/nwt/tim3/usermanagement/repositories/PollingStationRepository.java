package ba.nwt.tim3.usermanagement.repositories;

import ba.nwt.tim3.usermanagement.entities.PollingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollingStationRepository extends JpaRepository<PollingStation,Long> {
}
