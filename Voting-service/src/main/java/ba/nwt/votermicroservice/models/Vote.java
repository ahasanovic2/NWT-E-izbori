package ba.nwt.votermicroservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer voterId;
    private Integer electionId;
    private Integer candidateId;
    private Integer listaId;
    private LocalDateTime timestamp;
}