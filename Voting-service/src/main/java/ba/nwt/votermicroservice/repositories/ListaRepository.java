package ba.nwt.votermicroservice.repositories;

import ba.nwt.votermicroservice.models.Lista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListaRepository extends JpaRepository<Lista,Long> {

    List<Lista> findAllByElectionId(Long electionId);
}
