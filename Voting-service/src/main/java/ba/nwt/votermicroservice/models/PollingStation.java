package ba.nwt.votermicroservice.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PollingStation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "This field cannot be null")
    @Size(min = 3, message = "This field must contain at least 3 character")
    private String name;


    @NotNull(message = "This field cannot be null")
    @Size(min = 5, message = "This field must contain at least 5 character")
    private String adress;

    @JsonIgnore
    @OneToMany(mappedBy = "pollingStation")
    private List<ElectionPollingStation> electionPollingStations = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "pollingStation")
    private List<Voter> voters = new ArrayList<Voter>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }



    public PollingStation() {
    }

    public PollingStation(Long id, ArrayList<ElectionPollingStation> electionPollingStations, List<Voter> voter) {
        this.id = id;
        this.electionPollingStations = electionPollingStations;
        this.voters = voter;
    }

    public List<ElectionPollingStation> getElectionPollingStations() {
        return electionPollingStations;
    }

    public void setElectionPollingStations(List<ElectionPollingStation> electionPollingStations) {
        this.electionPollingStations = electionPollingStations;
    }

    public List<Voter> getVoters() {
        return voters;
    }

    public void setVoters(List<Voter> voters) {
        this.voters = voters;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
