package ba.nwt.electionmanagement.repositories;

import ba.nwt.electionmanagement.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {

    List<Election> findByPollingStationElectionId(Long pollingStationElectionId);
}
