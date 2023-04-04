package ba.nwt.tim3.models;

import ba.nwt.tim3.models.Candidate;
import ba.nwt.tim3.models.Election;
import ba.nwt.tim3.models.List;
import ba.nwt.tim3.models.PollingStation;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Result {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "result_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )

    private int id;
    private int vote_count;


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

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
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
