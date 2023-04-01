package ba.nwt.electionmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Election {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "election_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9]+(\\s+[a-zA-Z0-9]+)*$", message = "You can only enter alphabet characters and numbers.")
    private String name;

    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Pattern(regexp = "^(Active|Finished|Not started)$", message = "This field can only be Active, Finished and Not started")
    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "election")
    private List<Lista> list = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "election")
    private List<PollingStation> pollingStations = new ArrayList<>();

    public Election() {

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
