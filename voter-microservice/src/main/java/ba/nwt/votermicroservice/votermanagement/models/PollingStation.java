package ba.nwt.votermicroservice.votermanagement.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PollingStation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
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
