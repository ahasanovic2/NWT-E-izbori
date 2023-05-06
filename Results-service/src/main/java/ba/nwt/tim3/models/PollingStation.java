package ba.nwt.tim3.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PollingStation {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "pollingStation_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    @NotNull(message = "This field cannot be null")
    @Size(min = 2, message = "This field must be at least 2 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+(\\s+[a-zA-Z0-9]+)*$", message = "You can only enter alphabet characters and numbers.")
    private String name;

    @NotNull(message = "This field cannot be null")
    @Size(min = 2, message = "This field must be at least 2 characters")
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
    private List<Result> results = new ArrayList<>();

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

    public void setAddress(String adress) {
        this.address = adress;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
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
