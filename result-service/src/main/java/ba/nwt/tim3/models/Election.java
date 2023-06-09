package ba.nwt.tim3.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class Election {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;

    public boolean isEndTime() {
        if (endTime == null || startTime == null) {
            return true;
        }
        return endTime.isAfter(startTime);
    }
}
