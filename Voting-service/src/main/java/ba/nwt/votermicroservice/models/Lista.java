package ba.nwt.votermicroservice.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Lista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "This field cannot be null")
    @NotBlank(message = "This field cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9\\-]+(\\s+[a-zA-Z0-9\\-]+)*$", message = "You can only enter alphabet characters, numbers, and hyphens.")
    private String name;

    @NotNull(message = "This field cannot be null")
    private Boolean nezavisna;

    @JsonIgnore
    @OneToMany(mappedBy = "lista")
    private List<Candidate> candidates = new ArrayList<>();
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

    public Boolean getNezavisna() {
        return nezavisna;
    }

    public void setNezavisna(Boolean nezavisna) {
        this.nezavisna = nezavisna;
    }

    public void addCandidates(Candidate candidate1) {
        this.candidates.add(candidate1);
    }
}
