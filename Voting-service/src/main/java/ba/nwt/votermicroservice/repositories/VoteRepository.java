package ba.nwt.votermicroservice.repositories;


import ba.nwt.votermicroservice.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {
    List<Vote> findAllByElectionId(Integer electionId);
    Optional<Vote> getVoteByElectionIdAndAndVoterId(Integer electionId, Integer voterId);
}
