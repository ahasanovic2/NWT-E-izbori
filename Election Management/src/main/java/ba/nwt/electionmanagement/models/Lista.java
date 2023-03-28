package ba.nwt.electionmanagement.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Lista {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "lista", cascade = CascadeType.ALL)
    private List<Kandidat> candidates = new ArrayList<>();

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

    public List<Kandidat> getCandidates() {
        return candidates;
    }

    public void setCandidates(ArrayList<Kandidat> candidates) {
        this.candidates = candidates;
    }

    public void addCandidates(Kandidat kandidat) { this.candidates.add(kandidat); }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }
}
