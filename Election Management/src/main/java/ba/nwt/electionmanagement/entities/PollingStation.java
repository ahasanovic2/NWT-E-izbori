package ba.nwt.electionmanagement.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class PollingStation {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "ps_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    private Long id;

    private String name;
    private String address;

    @OneToMany(mappedBy = "pollingStation")
    private List<User> voters = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "polling_station_election",
            joinColumns = @JoinColumn(name = "polling_station_id"),
            inverseJoinColumns = @JoinColumn(name = "election_id"))
    private Set<Election> elections = new HashSet<>();

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

    public Set<Election> getElections() {
        return elections;
    }

    public void setElections(Set<Election> elections) {
        this.elections = elections;
    }
    public void addElections(Election election) {
        this.elections.add(election);
    }
}
