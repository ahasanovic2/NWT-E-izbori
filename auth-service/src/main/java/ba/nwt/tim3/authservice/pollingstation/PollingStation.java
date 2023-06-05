package ba.nwt.tim3.authservice.pollingstation;

import ba.nwt.tim3.authservice.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class PollingStation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

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

    @Nullable
    @Pattern(regexp = "^((Unsko Sanski)|(Posavski)|(Tuzlanski)|(Zenicko Dobojski)|(Bosansko Podrinjski)|(Srednjobosanski)|(Hercegovacko neretvanski)|(Zapadnohercegovacki)|(Sarajevo)|(Kanton 10))$", message = "Kanton must be one of predefined values")
    private String kanton;

    @NotNull(message = "Opcina cannot be null")
    @NotBlank(message = "Opcina cannot be blank")
    private String opcina;

    @JsonIgnore
    @OneToMany(mappedBy = "pollingStation")
    private List<User> users = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<User> getUser() {
        return users;
    }

    public void setUser(ArrayList<User> voters) {
        this.users = voters;
    }

    public void addUser(User voter) {
        this.users.add(voter);
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id + "," +
                "\"name\":\"" + name + "\"," +
                "\"address\":\"" + address + "\"," +
                "\"entitet\":\"" + entitet + "\"," +
                "\"kanton\":\"" + kanton + "\"," +
                "\"opcina\":\"" + opcina + "\"" +
                "}";
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