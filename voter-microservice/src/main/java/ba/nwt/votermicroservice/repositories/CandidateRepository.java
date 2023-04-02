package ba.nwt.votermicroservice.repositories;

import ba.nwt.votermicroservice.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {
    List<Candidate> findAllByListaId(Long listaId);
}
