package ba.nwt.tim3.notificationmicroservices.management.repositories;

import ba.nwt.tim3.notificationmicroservices.management.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result,Long> {
}
