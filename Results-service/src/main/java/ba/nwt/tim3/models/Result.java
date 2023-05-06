package ba.nwt.tim3.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
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

    private Long id;

    @Min(value = 0, message = "Vote count cannot be less than zero")
    @Digits(integer = 15, fraction = 0, message = "Vote count must be a number")
    private int voteCount;


    @ManyToOne
    @JoinColumn(name = "listId")
    private List list;

    @ManyToOne
    @JoinColumn(name = "pollingStationId")
    private PollingStation pollingStation;

    @ManyToOne
    @JoinColumn(name = "candidateId")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "election_id")
    private Election election;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int vote_count) {
        this.voteCount = vote_count;
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

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id +
                "\", \"vote_count\":\"" + voteCount + '\"' +
                ", \"candidate\":\"" + candidate +
                "\", \"pollingStation\":\"" + pollingStation +
                "\", \"list\":\"" + list + '\"' +
                ", \"election\":\"" + election + '\"' +
                '}';
    }
}
