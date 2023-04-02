package ba.nwt.votermicroservice.repositories;

import ba.nwt.votermicroservice.models.PollingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollingStationRepository extends JpaRepository<PollingStation,Long> {
}
