package ba.nwt.votermicroservice.interfaces;


import ba.nwt.votermicroservice.votermanagement.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {
    List<Vote> findAllByElectionId(Long electionId);
}
