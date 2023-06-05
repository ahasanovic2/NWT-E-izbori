package ba.nwt.tim3.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;

@Entity
@Getter
@Setter
public class List {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int voteCount;

    private Boolean nezavisna;

    @NotNull(message = "This field cannot be null")
    @Size(min = 2, message = "This field must be at least 2 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+(\\s+[a-zA-Z0-9]+)*$", message = "You can only enter alphabet characters and numbers.")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "list")
    private java.util.List<Result> results = new ArrayList<>();
}
