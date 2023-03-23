package ba.nwt.tim3.models;

import ba.nwt.tim3.models.Candidate;
import ba.nwt.tim3.models.Election;
import ba.nwt.tim3.models.List;
import ba.nwt.tim3.models.PollingStation;
import jakarta.persistence.*;

@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Long vote_count;


    @OneToOne
    @JoinColumn(name = "candidateId")
    private Candidate candidate;

    @OneToOne
    @JoinColumn(name = "pollingStationId")
    private PollingStation pollingStation;

    @OneToOne
    @JoinColumn(name = "listId")
    private List list;

    @ManyToOne
    @JoinColumn(name = "election_id")
    private Election election;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getVote_count() {
        return vote_count;
    }

    public void setVote_count(Long vote_count) {
        this.vote_count = vote_count;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public PollingStation getPollingStation() {
        return pollingStation;
    }

    public void setPollingStation(PollingStation pollingStation) {
        this.pollingStation = pollingStation;
    }
}
