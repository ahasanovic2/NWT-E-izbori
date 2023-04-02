package ba.nwt.votermicroservice.votermanagement.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="voter_id", nullable=false)
    private Voter voter;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "election_id")
    private Election election;


    @ManyToOne(cascade = CascadeType.ALL)
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

    public Voter getVoter() {
        return voter;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public Lista getLista() {
        return lista;
    }

    public void setListe(Lista lista) {
        this.lista = lista;
    }

    private LocalDateTime timestamp;








}
