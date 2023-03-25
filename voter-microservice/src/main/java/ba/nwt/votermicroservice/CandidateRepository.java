package ba.nwt.votermicroservice;

import ba.nwt.votermicroservice.votermanagement.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {
}
