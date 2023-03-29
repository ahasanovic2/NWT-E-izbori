package ba.nwt.electionmanagement.interfaces;

import ba.nwt.electionmanagement.models.Election;
import ba.nwt.electionmanagement.models.Lista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListaRepository extends JpaRepository<Lista, Long> {

    List<Lista> findAllByElectionId(Long electionId);
}
