package ba.nwt.votermicroservice.votermanagement.models;


import jakarta.persistence.*;

@Entity
public class ElectionPollingStation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long PoolingStationID;

    @ManyToOne
    @JoinColumn(name = "election")
    private Election election;

    @ManyToOne
    @JoinColumn(name = "pollingstation")
    private PollingStation pollingstation;

    public Long getPoolingStationID() {
        return PoolingStationID;
    }

    public void setPoolingStationID(Long poolingStationID) {
        PoolingStationID = poolingStationID;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public PollingStation getPoolingStation() {
        return pollingstation;
    }

    public void setPoolingStation(PollingStation pollingStation) {
        this.pollingstation = pollingStation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
