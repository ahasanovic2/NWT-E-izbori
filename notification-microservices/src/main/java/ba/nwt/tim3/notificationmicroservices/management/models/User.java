package ba.nwt.tim3.notificationmicroservices.management.models;


import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String first_name;
    private String last_name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "notification_id")
    private Notification notification;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pollingstation_id")
    private PollingStation pollingStation;

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }


    public Long getId() {
        return id;
    }
}
