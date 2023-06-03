package ba.nwt.electionmanagement.repositories;

import ba.nwt.electionmanagement.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {

    @Query("SELECT e FROM Election e JOIN e.pollingStations ps WHERE ps.name = :name")
    List<Election> findElectionsByPollingStationName(@Param("name") String name);
}
