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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "This field cannot be null")
    @Size(min = 2, message = "This field must be at least 2 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+(\\s+[a-zA-Z0-9]+)*$", message = "You can only enter alphabet characters and numbers.")
    private String name;


    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    @NotNull(message = "This field cannot be null")
    private Boolean nezavisna;

    @JsonIgnore
    @OneToMany(mappedBy = "lista")
    private List<Candidate> candidates = new ArrayList<>();

    @JsonIgnore
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

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id + "," +
                "\"name\":\"" + name + "\"," +
                "\"nezavisna\":" + nezavisna +
                "}";
    }

}
