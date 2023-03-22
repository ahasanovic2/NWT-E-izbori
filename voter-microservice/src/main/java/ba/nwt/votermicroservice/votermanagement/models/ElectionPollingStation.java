package ba.nwt.votermicroservice.votermanagement.models;


import jakarta.persistence.*;

@Entity
public class ElectionPollingStation {
    @Id
    @GeneratedValue
    private Long id;

    public Long getPollingStationID() {
        return PollingStationID;
    }

    public void setPollingStationID(Long pollingStationID) {
        PollingStationID = pollingStationID;
    }



    private Long PollingStationID;

    @ManyToOne
    @JoinColumn(name = "election_id")
    private Election election_polling_station;

    @ManyToOne
    @JoinColumn(name = "pollingstation_id")
    private PollingStation electionstation;

    public Election getElection_polling_station() {
        return election_polling_station;
    }

    public void setElection_polling_station(Election election_polling_station) {
        this.election_polling_station = election_polling_station;
    }

    public PollingStation getElectionstation() {
        return electionstation;
    }

    public void setElectionstation(PollingStation electionstation) {
        this.electionstation = electionstation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
