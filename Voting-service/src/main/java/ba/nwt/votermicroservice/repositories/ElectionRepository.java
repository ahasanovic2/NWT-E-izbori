package ba.nwt.votermicroservice.repositories;


import ba.nwt.votermicroservice.models.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectionRepository extends JpaRepository<Election,Long> {
}
