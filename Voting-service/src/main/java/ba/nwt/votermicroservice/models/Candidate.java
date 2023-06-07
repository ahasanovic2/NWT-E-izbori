package ba.nwt.votermicroservice.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Candidate {
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

    @NotNull(message = "This field cannot be null")
    @Size(min = 20, message = "This field must contain at least 20 characters.")
    private String description;

    @ManyToOne
    @JoinColumn(name = "lista_id")
    private Lista lista;

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    public Candidate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
