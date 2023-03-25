package ba.nwt.votermicroservice.votermanagement.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String first_name;
    private String description;


    @OneToMany(mappedBy = "election")
    private List<Vote> votes = new ArrayList<>();

    @OneToMany(mappedBy = "election")
    private ArrayList<ElectionPollingStation> electionPollingStations;
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

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public ArrayList<ElectionPollingStation> getElectionPollingStations() {
        return electionPollingStations;
    }

    public void setElectionPoolingStations(ArrayList<ElectionPollingStation> electionPollingStations) {
        this.electionPollingStations = electionPollingStations;
    }





    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
