package ba.nwt.votermicroservice.votermanagement.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
public class Vote {

    @Id
    @GeneratedValue
   private Long id;

    private LocalDateTime timestamp;



    @ManyToOne
    @JoinColumn(name = "lista_id")
    private Lista liste;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    public Lista getListe() {
        return liste;
    }

    public void setListe(Lista liste) {
        this.liste = liste;
    }

    public Election getVote() {
        return vote;
    }

    public void setVote(Election vote) {
        this.vote = vote;
    }

    @ManyToOne
    @JoinColumn(name = "election_id")
    private Election vote;




    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Election getElection() {
        return vote;
    }

    public void setElection(Election election) {
        this.vote = election;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
