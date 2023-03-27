package ba.nwt.tim3.notificationmicroservices.management.models;


import ba.nwt.tim3.notificationmicroservices.management.models.Notification;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private LocalDateTime start_time;
    private LocalDateTime end_time;

    @OneToMany(mappedBy = "election")
    private List<Result> resultList;

    public List<Result> getResultList() {
        return resultList;
    }

    public void addResult (Result result) {
        this.resultList.add(result);
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
