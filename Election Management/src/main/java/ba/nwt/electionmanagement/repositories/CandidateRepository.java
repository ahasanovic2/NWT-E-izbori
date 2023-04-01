package ba.nwt.electionmanagement.repositories;

import ba.nwt.electionmanagement.entities.Kandidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Kandidat, Long> {

}
