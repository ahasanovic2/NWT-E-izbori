package ba.nwt.votermicroservice.votermanagement.models;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity

public class Voter {
    @Id
    @GeneratedValue
    private Long id;
    private String first_name;
    private String last_name;
    private int poolingStationID;

    @OneToMany(mappedBy = "voter")
    private ArrayList<Lista> liste;

    @OneToOne(mappedBy = "voter")
    private ArrayList<PoolingStation> poolingStations;

    public ArrayList<Lista> getListe() {
        return liste;
    }

    public void setListe(ArrayList<Lista> liste) {
        this.liste = liste;
    }

    public ArrayList<PoolingStation> getPoolingStations() {
        return poolingStations;
    }

    public void setPoolingStations(ArrayList<PoolingStation> poolingStations) {
        this.poolingStations = poolingStations;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getPoolingStationID() {
        return poolingStationID;
    }

    public void setPoolingStationID(int poolingStationID) {
        this.poolingStationID = poolingStationID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @OneToMany(mappedBy = "voters")
    private Collection<Vote> vote;

    public Collection<Vote> getVote() {
        return vote;
    }

    public void setVote(Collection<Vote> vote) {
        this.vote = vote;
    }
}
