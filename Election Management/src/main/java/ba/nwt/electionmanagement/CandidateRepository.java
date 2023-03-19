package ba.nwt.electionmanagement;

import ba.nwt.electionmanagement.models.Election;
import ba.nwt.electionmanagement.models.Kandidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Kandidat, Long> {

}
