package ba.nwt.votermicroservice.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "This field cannot be null")
    @Size(min = 10, message = "This field must contain at least 10 character")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "election")
    private List<Vote> votes = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "election")
    private List<ElectionPollingStation> electionPollingStations;

    @JsonIgnore
    @OneToMany(mappedBy = "election")
    private List<Lista> lists = new ArrayList<>();


    public List<Lista> getLists() {
        return lists;
    }

    public void setLists(List<Lista> lists) {
        this.lists = lists;
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

    public List<ElectionPollingStation> getElectionPollingStations() {
        return electionPollingStations;
    }

    public void setElectionPoolingStations(List<ElectionPollingStation> electionPollingStations) {
        this.electionPollingStations = electionPollingStations;
    }





    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
