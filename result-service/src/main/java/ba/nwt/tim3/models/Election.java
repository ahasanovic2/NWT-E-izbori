package ba.nwt.tim3.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "This field cannot be null")
    @Size(min = 2, message = "This field must be at least 2 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+(\\s+[a-zA-Z0-9]+)*$", message = "You can only enter alphabet characters and numbers.")
    private String name;

    @NotNull(message = "This field cannot be null")
    private LocalDateTime startTime;

    @NotNull(message = "This field cannot be null")
    @Future(message = "End time must be after start time")
    private LocalDateTime endTime;

    @NotNull(message = "This field cannot be null")
    @Size(min = 20, message = "This field must contain at least 20 characters.")
    private String description;

    @NotBlank(message = "This field cannot be empty")
    @Pattern(regexp = "^(Active|Finished|NotStarted)$", message = "This field can only be Active, Finished and NotStarted")
    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "election")
    private List<Result> results = new ArrayList<>();
}
