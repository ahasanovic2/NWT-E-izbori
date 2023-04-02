package ba.nwt.electionmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "This field cannot be null")
    @NotBlank(message = "This field cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9\\-]+(\\s+[a-zA-Z0-9\\-]+)*$", message = "You can only enter alphabet characters, numbers, and hyphens.")
    private String name;


    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    private Boolean nezavisna;

    @JsonIgnore
    @OneToMany(mappedBy = "lista")
    private List<Candidate> candidates = new ArrayList<>();

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

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(ArrayList<Candidate> candidates) {
        this.candidates = candidates;
    }

    public void addCandidates(Candidate kandidat) { this.candidates.add(kandidat); }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }
    public Boolean getNezavisna() {
        return nezavisna;
    }

    public void setNezavisna(Boolean nezavisna) {
        this.nezavisna = nezavisna;
    }
}
