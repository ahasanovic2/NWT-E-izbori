package ba.nwt.tim3.interfaces;

import ba.nwt.tim3.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
    Optional<Result> getResultByCandidateFirstNameAndCandidateLastNameAndElectionNameAndPollingStationName(String candidateFirstName, String candidateLastName, String electionName, String pollingStationName);
    List<Result> getResultsByElectionName(String electionName);
    List<Result> getResultsByElectionNameAndPollingStationName(String electionName, String pollingStationName);
    Optional<Result> getResultsByListNameAndElectionName(String listName, String electionName);
    Optional<Result> getResultsByListNameAndElectionNameAndPollingStationName(String listName, String electionName, String pollingStationName);
    List<Result> getResultsByElectionNameAndCandidateFirstNameAndCandidateLastName(String electionName, String candidateFirstName, String CandidateLastName);
    Optional<Result> getResultsByElectionNameAndCandidateFirstNameAndCandidateLastNameAndPollingStationName(String electionName, String candidateFirstName, String candidateLastName, String pollingStationName);
    Optional<Result> getResultsByElectionNameAndListNameAndPollingStationName(String electionName, String listName, String pollingStationName);
}