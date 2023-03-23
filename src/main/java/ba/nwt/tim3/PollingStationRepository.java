package ba.nwt.tim3;

import ba.nwt.tim3.models.PollingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollingStationRepository extends JpaRepository<PollingStation, Long> {

}
