package ba.nwt.tim3.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id +
                "\", \"vote_count\":\"" + voteCount + '\"' +
                ", \"candidate\":\"" + candidate +
                "\", \"pollingStation\":\"" + pollingStation.getName() +
                "\", \"list\":\"" + list + '\"' +
                ", \"election\":\"" + election.getName() + '\"' +
                '}';
    }
}
