
package ba.nwt.electionmanagement.messaging;



import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ElectionMessage {


    @JsonProperty("id")
    private Long election_id;
    @JsonProperty("name")
    private String election_name;
    @JsonProperty("description")
    private String election_description;
    @JsonProperty("start_time")
    private String election_startTime;
    @JsonProperty("end_time")
    private String election_endTime;

    @JsonProperty("status")
    private String election_status;

    public ElectionMessage() {
    }

    public Long getElection_id() {
        return election_id;
    }

    public void setElection_id(Long election_id) {
        this.election_id = election_id;
    }

    public String getElection_name() {
        return election_name;
    }

    public void setElection_name(String election_name) {
        this.election_name = election_name;
    }

    public String getElection_description() {
        return election_description;
    }

    public void setElection_description(String election_description) {
        this.election_description = election_description;
    }

    public String getElection_startTime() {
        return election_startTime;
    }

    public void setElection_startTime(String election_startTime) {
        this.election_startTime = election_startTime;
    }

    public String getElection_endTime() {
        return election_endTime;
    }

    public void setElection_endTime(String election_endTime) {
        this.election_endTime = election_endTime;
    }

    public String getElection_status() {
        return election_status;
    }

    public void setElection_status(String election_status) {
        this.election_status = election_status;
    }
}
