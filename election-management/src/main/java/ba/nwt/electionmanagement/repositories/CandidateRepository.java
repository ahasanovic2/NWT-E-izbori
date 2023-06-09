package ba.nwt.electionmanagement.repositories;

import ba.nwt.electionmanagement.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    List<Candidate> getCandidatesByElectionName(String name);
    Optional<Candidate> getCandidateByFirstNameAndLastName(String firstName, String lastName);
}
