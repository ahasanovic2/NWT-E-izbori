package ba.nwt.tim3.notificationmicroservices.management;

import ba.nwt.tim3.notificationmicroservices.management.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
