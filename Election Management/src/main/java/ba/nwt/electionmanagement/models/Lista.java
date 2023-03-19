package ba.nwt.electionmanagement.models;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Lista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "lista")
    private ArrayList<Kandidat> candidates;

    @ManyToOne
    @JoinColumn(name = "election_id")
    private Election election;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Kandidat> getCandidates() {
        return candidates;
    }

    public void setCandidates(ArrayList<Kandidat> candidates) {
        this.candidates = candidates;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }
}
