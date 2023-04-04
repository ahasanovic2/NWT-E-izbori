package ba.nwt.tim3.interfaces;

import ba.nwt.tim3.models.PollingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PollingStationRepository extends JpaRepository<PollingStation, Long> {

}
