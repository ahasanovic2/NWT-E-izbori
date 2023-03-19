package ba.nwt.electionmanagement;

import ba.nwt.electionmanagement.models.Election;
import ba.nwt.electionmanagement.models.Lista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaRepository extends JpaRepository<Lista, Long> {

}
