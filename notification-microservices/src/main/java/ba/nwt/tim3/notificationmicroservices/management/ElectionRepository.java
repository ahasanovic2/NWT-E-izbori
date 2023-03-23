package ba.nwt.tim3.notificationmicroservices.management;

import ba.nwt.tim3.notificationmicroservices.management.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {
}
