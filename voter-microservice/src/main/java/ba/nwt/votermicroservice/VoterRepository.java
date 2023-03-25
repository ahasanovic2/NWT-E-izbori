package ba.nwt.votermicroservice;

import ba.nwt.votermicroservice.votermanagement.models.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterRepository extends JpaRepository<Voter,Long> {
}
