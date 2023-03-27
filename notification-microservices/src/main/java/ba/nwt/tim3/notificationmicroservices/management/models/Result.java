package ba.nwt.tim3.notificationmicroservices.management.models;

import jakarta.persistence.*;

@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Long voteCount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="election_id")
    private Election election;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="pollingstation_id")
    private PollingStation pollingStation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }
}
