package ba.nwt.electionmanagement.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PollingStation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String address;

    @OneToMany(mappedBy = "pollingStation")
    private List<User> voters = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "election_id")
    private Election election;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<User> getVoters() {
        return voters;
    }

    public void setVoters(ArrayList<User> voters) {
        this.voters = voters;
    }

    public void addVoter(User voter) {
        this.voters.add(voter);
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }
}
