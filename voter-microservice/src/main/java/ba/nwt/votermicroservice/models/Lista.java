package ba.nwt.votermicroservice.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Lista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "lista")
    private List<Candidate> candidates;
    @JsonIgnore
    @OneToMany(mappedBy = "lista")
    private List<Vote> votes;

    @ManyToOne
    @JoinColumn(name="electionId")
    private Election election;


    public List<Candidate> getCandidates() {
        return candidates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
