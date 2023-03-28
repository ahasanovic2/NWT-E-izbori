package ba.nwt.electionmanagement.models;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    private String name;

    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;

    @OneToMany(mappedBy = "election")
    private List<Lista> list = new ArrayList<>();

    @OneToMany(mappedBy = "election")
    private List<PollingStation> pollingStations = new ArrayList<>();

    public Election() {

    }

    public Election(String name, String description, String status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Election(Long id, String name, String description, LocalDateTime start_time, LocalDateTime end_time, String status, List<Lista> list, List<User> voters, List<PollingStation> pollingStations) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startTime = start_time;
        this.endTime = end_time;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime start_time) {
        this.startTime = start_time;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime end_time) {
        this.endTime = end_time;
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

    public void setStartTime(String startTime) {
        this.startTime = LocalDateTime.parse(startTime, DateTimeFormatter.ISO_DATE_TIME);
    }

    public void setEndTime(String endTime) {
        this.endTime = LocalDateTime.parse(endTime,DateTimeFormatter.ISO_DATE_TIME);
    }
}