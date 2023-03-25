package ba.nwt.votermicroservice.votermanagement.models;


import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Lista {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToMany(mappedBy = "lista")
    private ArrayList<Candidate> candidates;

    @OneToMany(mappedBy = "lista")
    private ArrayList<Vote> votes;


    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(ArrayList<Candidate> candidates) {
        this.candidates = candidates;
    }

    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public void setVotes(ArrayList<Vote> votes) {
        this.votes = votes;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
