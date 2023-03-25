package ba.nwt.votermicroservice.votermanagement.models;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity

public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String first_name;
    private String last_name;


    @OneToMany(mappedBy = "voter")
    private List<Vote> votes = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private PollingStation pollingStation;

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> liste) {
        this.votes = votes;
    }

    public PollingStation getPoolingStations() {
        return pollingStation;
    }

    public void setPoolingStations(PollingStation pollingStation) {
        this.pollingStation = pollingStation;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }



}
