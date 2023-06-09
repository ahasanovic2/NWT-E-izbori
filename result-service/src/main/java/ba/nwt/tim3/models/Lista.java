package ba.nwt.tim3.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Lista {
    private Integer id;
    private String name;
    private String description;

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id + "," +
                "\"name\":\"" + name + "\"," +
                "\"description\":" + description +
                "}";
    }

}
