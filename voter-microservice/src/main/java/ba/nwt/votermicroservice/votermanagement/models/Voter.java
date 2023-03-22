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


    @OneToOne(mappedBy = "voter")
    private PollingStation pollingStations;

    @OneToMany(mappedBy = "vote")
    private ArrayList<Vote> votes;

    public PollingStation getPoolingStations() {
        return pollingStations;
    }

    public void setPoolingStations(PollingStation pollingStations) {
        this.pollingStations = pollingStations;
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



    public Collection<Vote> getVote() {
        return votes;
    }

    public void setVote(ArrayList<Vote> vote) {
        this.votes = vote;
    }
}
