package ba.nwt.tim3.authservice.pollingstation;

import ba.nwt.tim3.authservice.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PollingStationRepository extends JpaRepository<PollingStation,Integer> {
    Optional<PollingStation> findByName(String name);
    Optional<PollingStation> getPollingStationByUsers(User user);
}
