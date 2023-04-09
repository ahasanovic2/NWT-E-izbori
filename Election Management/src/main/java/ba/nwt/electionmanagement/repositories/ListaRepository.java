package ba.nwt.electionmanagement.repositories;

import ba.nwt.electionmanagement.entities.Lista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListaRepository extends JpaRepository<Lista, Long> {

    List<Lista> findAllByElectionId(Long electionId);
}
