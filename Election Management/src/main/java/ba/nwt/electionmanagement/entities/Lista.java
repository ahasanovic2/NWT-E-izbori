package ba.nwt.electionmanagement.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Lista {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "list_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "lista", cascade = CascadeType.ALL)
    private List<Kandidat> candidates = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "electionId")
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
