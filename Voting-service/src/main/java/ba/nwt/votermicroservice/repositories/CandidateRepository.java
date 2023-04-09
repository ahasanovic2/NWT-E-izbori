package ba.nwt.votermicroservice.repositories;

import ba.nwt.votermicroservice.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {
    List<Candidate> findAllByListaId(Long listaId);

    Optional<Candidate> findByIdAndListaId(Long candidateId, Long listaId);
}
