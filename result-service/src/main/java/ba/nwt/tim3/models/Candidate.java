package ba.nwt.tim3.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Candidate {
    private Integer id;
    private String firstName;
    private String lastName;
    private String description;
    private Integer electionId;

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id + "," +
                "\"firstName\":\"" + firstName + "\"," +
                "\"lastName\":\"" + lastName + "\"," +
                "\"description\":\"" + description.replace("\"", "\\\"") + "\"" +
                "}";
    }

}

