package ba.nwt.votermicroservice.votermanagement.models;


import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class PoolingStation {


    @Id
    @GeneratedValue
    private Long id;


    @OneToMany(mappedBy = "election_polling_station")
    private ArrayList<ElectionPollingStation> electionPollingStations;

    @OneToOne
    @JoinColumn(name = "voter_id")
    private Voter voter;

    public ArrayList<ElectionPollingStation> getElectionPollingStations() {
        return electionPollingStations;
    }

    public void setElectionPollingStations(ArrayList<ElectionPollingStation> electionPollingStations) {
        this.electionPollingStations = electionPollingStations;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
