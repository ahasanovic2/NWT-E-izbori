package ba.nwt.tim3.notificationmicroservices.management.models;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ErrorMessage {
    private int status;
    private String message;
}