package ba.nwt.votermicroservice.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "This field cannot be null")
    @Size(min = 1, message = "This field must contain at least 1 character")
    @Pattern(regexp = "^[a-zA-Z0-9]", message = "You can only enter alphabet characters.")
    private String first_name;
    @NotNull(message = "This field cannot be null")
    @Size(min = 1, message = "This field must contain at least 1 character")
    @Pattern(regexp = "^[a-zA-Z0-9]", message = "You can only enter alphabet characters.")
    private String last_name;

    @OneToMany(mappedBy = "candidate")
    private List<Vote> votes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "lista_id")
    private Lista lista;

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

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
}
