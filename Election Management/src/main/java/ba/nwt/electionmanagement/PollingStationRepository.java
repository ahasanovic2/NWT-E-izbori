package ba.nwt.electionmanagement;

import ba.nwt.electionmanagement.models.Election;
import ba.nwt.electionmanagement.models.PollingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollingStationRepository extends JpaRepository<PollingStation, Long> {

}
