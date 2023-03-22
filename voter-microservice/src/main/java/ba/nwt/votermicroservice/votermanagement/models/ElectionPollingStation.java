package ba.nwt.votermicroservice.votermanagement.models;


import jakarta.persistence.*;

@Entity
public class ElectionPollingStation {
    @Id
    @GeneratedValue
    private Long id;
    private Long PoolingStationID;

    @ManyToOne
    @JoinColumn(name = "election_id")
    private Election election;

    @ManyToOne
    @JoinColumn(name = "poolingstation_id")
    private PoolingStation poolingStation;

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

    public PoolingStation getPoolingStation() {
        return poolingStation;
    }

    public void setPoolingStation(PoolingStation poolingStation) {
        this.poolingStation = poolingStation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
