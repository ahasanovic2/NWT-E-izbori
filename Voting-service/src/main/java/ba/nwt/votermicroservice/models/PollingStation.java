package ba.nwt.votermicroservice.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PollingStation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "This field cannot be null")
    @Column(unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9]+(\\s+[a-zA-Z0-9]+)*$", message = "You can only enter alphabet characters and numbers.")
    private String name;

    @NotNull(message = "This field cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9]+(\\s+[a-zA-Z0-9]+)*$", message = "You can only enter alphabet characters and numbers.")
    private String address;

    @NotBlank(message = "Entitet cannot be blank")
    @Pattern(regexp = "^(RepublikaSrpska|FederacijaBiH)$")
    private String entitet;

    @Pattern(regexp = "^((Unsko Sanski)|(Posavski)|(Tuzlanski)|(Zenicko Dobojski)|(Bosansko Podrinjski)|(Srednjobosanski)|(Hercegovacko neretvanski)|(Zapadnohercegovacki)|(Sarajevo)|(Kanton 10))$", message = "Kanton must be one of predefined values")
    private String kanton;

    @NotNull(message = "Opcina cannot be null")
    @NotBlank(message = "Opcina cannot be blank")
    private String opcina;


    @JsonIgnore
    @OneToMany(mappedBy = "pollingStation")
    private List<ElectionPollingStation> electionPollingStations = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "pollingStation")
    private List<Voter> voters = new ArrayList<Voter>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }



    public PollingStation() {
    }

    public PollingStation(Long id, ArrayList<ElectionPollingStation> electionPollingStations, List<Voter> voter) {
        this.id = id;
        this.electionPollingStations = electionPollingStations;
        this.voters = voter;
    }

    public List<ElectionPollingStation> getElectionPollingStations() {
        return electionPollingStations;
    }

    public void setElectionPollingStations(List<ElectionPollingStation> electionPollingStations) {
        this.electionPollingStations = electionPollingStations;
    }

    public List<Voter> getVoters() {
        return voters;
    }

    public void setVoters(List<Voter> voters) {
        this.voters = voters;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public String getEntitet() {
        return entitet;
    }

    public void setEntitet(String entitet) {
        this.entitet = entitet;
    }

    public String getKanton() {
        return kanton;
    }

    public void setKanton(String kanton) {
        this.kanton = kanton;
    }

    public String getOpcina() {
        return opcina;
    }

    public void setOpcina(String opcina) {
        this.opcina = opcina;
    }
}
