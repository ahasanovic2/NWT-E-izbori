package ba.nwt.votermicroservice.interfaces;

import ba.nwt.votermicroservice.votermanagement.models.Lista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListaRepository extends JpaRepository<Lista,Long> {

    List<Lista> findAllByElectionId(Long electionId);
}
