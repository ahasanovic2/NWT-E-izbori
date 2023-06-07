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



    @ManyToOne
    @JoinColumn(name = "lista_id")
    private Lista lista;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    public Lista getLista() {
        return lista;
    }

    public void setListe(Lista lista) {
        this.lista = lista;
    }

    private LocalDateTime timestamp;
}