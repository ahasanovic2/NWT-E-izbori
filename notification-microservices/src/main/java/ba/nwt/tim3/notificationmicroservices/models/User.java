package ba.nwt.tim3.notificationmicroservices.models;


import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;

    @OneToOne(mappedBy = "user")
    private Notification notification;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
