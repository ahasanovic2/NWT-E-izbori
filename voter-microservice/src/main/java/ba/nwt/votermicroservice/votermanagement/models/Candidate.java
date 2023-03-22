package ba.nwt.votermicroservice.votermanagement.models;


import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Candidate {
    @Id
    @GeneratedValue
    private Long id;
    private int listaID;
    private int electionID;
    private String first_name;
    private String last_name;

    @OneToMany(mappedBy = "candidate")
    private ArrayList<Vote> votes;

    @ManyToOne
    @JoinColumn(name = "lista_id")
    private Lista liste;

    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public void setVotes(ArrayList<Vote> votes) {
        this.votes = votes;
    }

    public Lista getListe() {
        return liste;
    }

    public void setListe(Lista liste) {
        this.liste = liste;
    }

    public Candidate() {
    }


    public int getListaID() {
        return listaID;
    }

    public void setListaID(int listaID) {
        this.listaID = listaID;
    }

    public int getElectionID() {
        return electionID;
    }

    public void setElectionID(int electionID) {
        this.electionID = electionID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
