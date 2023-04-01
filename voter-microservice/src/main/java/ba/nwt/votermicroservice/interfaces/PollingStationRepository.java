package ba.nwt.votermicroservice.interfaces;

import ba.nwt.votermicroservice.votermanagement.models.PollingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollingStationRepository extends JpaRepository<PollingStation,Long> {
}
