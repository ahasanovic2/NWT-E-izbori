package ba.nwt.tim3.notificationmicroservices.management.models;


import jakarta.persistence.*;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Notification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String type;
    private String message;
    private LocalDateTime timestamp;
    @OneToOne(cascade = CascadeType.ALL)
    private Election election;
    @OneToMany(cascade = CascadeType.ALL)
    private List<User> user;
    public List<User> getUsers() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }


    public void addUser(User user) { this.user.add(user); }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }
}
