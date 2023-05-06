package ba.nwt.electionmanagement.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class PollingStation {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "ps_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
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



    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "polling_station_election",
            joinColumns = @JoinColumn(name = "polling_station_id"),
            inverseJoinColumns = @JoinColumn(name = "election_id"))
    private Set<Election> elections = new HashSet<>();

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Election> getElections() {
        return elections;
    }

    public void setElections(Set<Election> elections) {
        this.elections = elections;
    }
    public void addElections(Election election) {
        this.elections.add(election);
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
