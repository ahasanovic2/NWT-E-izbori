package ba.nwt.votermicroservice.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity

public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "This field cannot be null")
    @Size(min = 1, message = "This field must contain at least 1 character")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "You can only enter alphabet characters.")
    private String firstName;

    @NotNull(message = "This field cannot be null")
    @Size(min = 1, message = "This field must contain at least 1 character")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "You can only enter alphabet characters.")
    private String lastName;

    @JsonIgnore
    @OneToMany(mappedBy = "voter")
    private List<Vote> votes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="pollingStationId")
    private PollingStation pollingStation;

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> liste) {
        this.votes = votes;
    }

    public PollingStation getPollingStation() {
        return pollingStation;
    }

    public void setPollingStation(PollingStation pollingStation) {
        this.pollingStation = pollingStation;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
