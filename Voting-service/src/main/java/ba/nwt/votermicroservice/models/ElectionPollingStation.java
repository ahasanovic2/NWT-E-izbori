package ba.nwt.votermicroservice.models;


import jakarta.persistence.*;

@Entity
public class ElectionPollingStation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "election")
    private Election election;

    @ManyToOne
    @JoinColumn(name = "pollingStationId")
    private PollingStation pollingStation;



    public PollingStation getPollingStation() {
        return pollingStation;
    }

    public void setPollingStation(PollingStation pollingStation) {
        this.pollingStation = pollingStation;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public PollingStation getPoolingStation() {
        return pollingStation;
    }

    public void setPoolingStation(PollingStation pollingStation) {
        this.pollingStation = pollingStation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
