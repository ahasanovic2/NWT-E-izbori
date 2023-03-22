package ba.nwt.votermicroservice.votermanagement.models;


import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Lista {
    @Id
    @GeneratedValue
    private Long id;


    @OneToMany(mappedBy = "liste")
    private ArrayList<Candidate> liste;

    @OneToMany(mappedBy = "liste")
    private ArrayList<Vote> votes;


    public ArrayList<Candidate> getListe() {
        return liste;
    }

    public void setListe(ArrayList<Candidate> liste) {
        this.liste = liste;
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
