package ba.nwt.votermicroservice;


import ba.nwt.votermicroservice.votermanagement.models.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectionRepository extends JpaRepository<Election,Long> {
}
