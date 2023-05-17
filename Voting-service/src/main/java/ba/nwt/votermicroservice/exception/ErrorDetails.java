package ba.nwt.votermicroservice.exception;
import java.time.LocalDateTime;

public class ErrorDetails {
    private String timestamp;
    private String message;
    private String field;

    public ErrorDetails(String timestamp, String field, String message) {
        super();
        this.timestamp = timestamp;
        this.field = field;
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
                "\"timestamp\":\"" + timestamp +
                "\", \"field\":\"" + field + '\"' +
                ", \"message\":\"" + message +
                "\"}";
    }
}
