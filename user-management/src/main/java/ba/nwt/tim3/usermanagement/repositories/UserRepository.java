package ba.nwt.tim3.usermanagement.repositories;

import ba.nwt.tim3.usermanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
