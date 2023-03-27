package ba.nwt.tim3.notificationmicroservices.management;

import ba.nwt.tim3.notificationmicroservices.management.models.PollingStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollingStationRepository extends JpaRepository<PollingStation, Long> {

}
