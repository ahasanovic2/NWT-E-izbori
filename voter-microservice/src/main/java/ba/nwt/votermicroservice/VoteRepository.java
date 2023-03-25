package ba.nwt.votermicroservice;

import ba.nwt.votermicroservice.votermanagement.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {
}
