package ba.nwt.tim3.interfaces;

import ba.nwt.tim3.models.PollingStation;
import ba.nwt.tim3.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    Optional<Result> findByElectionIdAndListId(Long election_id, Long listId);
    Optional<Result> findByElectionIdAndCandidateId(Long election_id, Long candidateId);

    List<Result> findAllByPollingStationId(Long pollingStationId);

    Optional<Result> findByPollingStationIdAndElectionIdAndCandidateId(Long electionId, Long pollingStationId, Long candidateId);

    Optional<Result> findByPollingStationIdAndElectionIdAndListId(Long electionId, Long pollingStationId, Long listId);

    //Optional<Result> findByElectionIdAndListId(Long election_id, Long listId);
}
