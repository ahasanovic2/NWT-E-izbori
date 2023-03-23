package ba.nwt.tim3.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String name;
    private LocalDateTime start_time;
    private LocalDateTime end_time;

    @OneToMany
    @JoinColumn(name = "election_id", insertable = false, updatable = false)
    private List<Result> result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Result> getResult() {
        return result;
    }

    public void addResult(Result result) {
        this.result.add(result);
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
}
