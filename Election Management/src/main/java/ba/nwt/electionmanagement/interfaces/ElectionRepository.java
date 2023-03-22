package ba.nwt.electionmanagement.interfaces;

import ba.nwt.electionmanagement.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {

}
