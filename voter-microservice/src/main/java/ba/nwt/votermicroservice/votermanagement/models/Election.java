package ba.nwt.votermicroservice.votermanagement.models;


import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Election {
    @Id
    @GeneratedValue
    private Long id;
    private String first_name;
    private String description;


    @OneToMany(mappedBy = "vote")
    private ArrayList<Vote> votes;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public void setVotes(ArrayList<Vote> votes) {
        this.votes = votes;
    }

    public ArrayList<ElectionPollingStation> getElectionPoolingStations() {
        return electionPoolingStations;
    }

    public void setElectionPoolingStations(ArrayList<ElectionPollingStation> electionPoolingStations) {
        this.electionPoolingStations = electionPoolingStations;
    }

    @OneToMany(mappedBy = "election_pooling_station")
    private ArrayList<ElectionPollingStation> electionPoolingStations;



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
