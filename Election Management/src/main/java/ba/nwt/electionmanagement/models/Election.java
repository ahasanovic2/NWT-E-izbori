package ba.nwt.electionmanagement.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private String description;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private String status;

    @OneToMany(mappedBy = "election")
    private ArrayList<Lista> list;

    @OneToMany(mappedBy = "election")
    private ArrayList<User> voters;

    @OneToMany(mappedBy = "election")
    private ArrayList<PollingStation> pollingStations;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Lista> getList() {
        return list;
    }

    public void setList(ArrayList<Lista> list) {
        this.list = list;
    }

    public ArrayList<User> getVoters() {
        return voters;
    }

    public void setVoters(ArrayList<User> voters) {
        this.voters = voters;
    }

    public ArrayList<PollingStation> getPollingStations() {
        return pollingStations;
    }

    public void setPollingStations(ArrayList<PollingStation> pollingStations) {
        this.pollingStations = pollingStations;
    }
}
