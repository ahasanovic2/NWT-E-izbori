package ba.nwt.electionmanagement.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    private String name;

    private String description;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private String status;

    @OneToMany(mappedBy = "election")
    private List<Lista> list = new ArrayList<>();

    @OneToMany(mappedBy = "election")
    private List<PollingStation> pollingStations = new ArrayList<>();

    public Election() {

    }

    public Election(Long id, String name, String description, LocalDateTime start_time, LocalDateTime end_time, String status, List<Lista> list, List<User> voters, List<PollingStation> pollingStations) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.status = status;
        this.list = list;
        this.pollingStations = pollingStations;
    }

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

    public List<Lista> getList() {
        return list;
    }

    public void setList(List<Lista> list) {
        this.list = list;
    }

    public void addLista(Lista list) {
        this.list.add(list);
    }

    public List<PollingStation> getPollingStations() {
        return pollingStations;
    }

    public void addPollingStation(PollingStation pollingStation) {
        this.pollingStations.add(pollingStation);
    }

    public void setPollingStations(List<PollingStation> pollingStations) {
        this.pollingStations = pollingStations;
    }
}
