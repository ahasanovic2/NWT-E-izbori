package ba.nwt.votermicroservice.interfaces;

import ba.nwt.votermicroservice.votermanagement.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {
    List<Candidate> findAllByListaId(Long listaId);
}
