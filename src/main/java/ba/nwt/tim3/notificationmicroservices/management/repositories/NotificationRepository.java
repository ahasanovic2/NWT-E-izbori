package ba.nwt.tim3.notificationmicroservices.management.repositories;


import ba.nwt.tim3.notificationmicroservices.management.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
//@Transactional(readOnly = true)
public interface NotificationRepository extends JpaRepository<Notification,Long> {

}
