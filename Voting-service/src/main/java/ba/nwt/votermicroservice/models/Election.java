package ba.nwt.votermicroservice.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "This field cannot be null")
    @NotBlank(message = "This field cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]+(\\s+[a-zA-Z0-9]+)*$", message = "You can only enter alphabet characters and numbers.")
    @Column(unique = true)
    private String name;

    @NotNull(message = "This field cannot be null")
    @Size(min = 20, message = "This field must contain at least 20 characters")
    private String description;

    @NotNull(message = "Start time must not be null")
    private String startTime;

    @NotNull(message = "This field cannot be null")
    @Future(message = "End time must be after start time")
    private String endTime;

    @NotBlank(message = "This field cannot be empty")
    @Pattern(regexp = "^(Active|Finished|NotStarted)$", message = "This field can only be Active, Finished and NotStarted")
    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "election")
    private List<Vote> votes = new ArrayList<>();

    @Override
    public String toString() {
        return "Election{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status='" + status + '\'' +
                ", votes=" + votes +
                ", electionPollingStations=" + electionPollingStations +
                ", lists=" + lists +
                '}';
    }

    @JsonIgnore
    @OneToMany(mappedBy = "election")
    private List<ElectionPollingStation> electionPollingStations;

    @JsonIgnore
    @OneToMany(mappedBy = "election")
    private List<Lista> lists = new ArrayList<>();


    public List<Lista> getLists() {
        return lists;
    }

    public void setLists(List<Lista> lists) {
        this.lists = lists;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public List<ElectionPollingStation> getElectionPollingStations() {
        return electionPollingStations;
    }

    public void setElectionPoolingStations(List<ElectionPollingStation> electionPollingStations) {
        this.electionPollingStations = electionPollingStations;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setElectionPollingStations(List<ElectionPollingStation> electionPollingStations) {
        this.electionPollingStations = electionPollingStations;
    }

    public void addLista(Lista lista1) {
        this.lists.add(lista1);
    }
}
