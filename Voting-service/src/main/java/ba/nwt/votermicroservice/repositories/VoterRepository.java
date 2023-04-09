package ba.nwt.votermicroservice.repositories;

import ba.nwt.votermicroservice.models.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoterRepository extends JpaRepository<Voter,Long> {
    List<Voter> findAllByPollingStationId(Long pollingStationId);
}
