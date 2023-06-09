package ba.nwt.tim3.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;


@Data
@Getter
@Setter
public class PollingStation {
    private Integer id;
    private String name;
    private String address;
    private String entitet;
    private String kanton;
    private String opcina;

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
}