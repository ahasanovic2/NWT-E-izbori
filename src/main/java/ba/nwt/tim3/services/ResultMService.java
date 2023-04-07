package ba.nwt.tim3.services;


import ba.nwt.tim3.exception.ResourceNotFoundException;
import ba.nwt.tim3.interfaces.PollingStationRepository;
import ba.nwt.tim3.interfaces.ResultRepository;
import ba.nwt.tim3.models.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ResultMService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    PollingStationRepository pollingStationRepository;

    public ResponseEntity<String> getResults() {
        List<Result> results = resultRepository.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            StringBuilder sb = new StringBuilder("[");
            for (Result result: results) {
                sb.append(result.toString()).append(",");
            }
            if (results.size() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("]");
            json = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error serializing elections to JSON: " + e.getMessage());
        }

        return ResponseEntity.ok(json);

    }

    public ResponseEntity<Result> getElectionResult(@PathVariable Long pollingStationId) throws ResourceNotFoundException {
        Optional<Result> electionResult = resultRepository.findById(pollingStationId);
        if (electionResult.isPresent()) {
            return ResponseEntity.ok().body(electionResult.get());
        } else {
            throw new ResourceNotFoundException("Result not found for polling station id"+pollingStationId);
        }
    }

    public ResponseEntity<Result> getListResult(@PathVariable Long election_id, @PathVariable Long listId) throws ResourceNotFoundException {
        Optional<Result> listResult = resultRepository.findByElectionIdAndListId(election_id, listId);
        if (listResult.isPresent()) {
            return ResponseEntity.ok().body(listResult.get());
        } else {
            throw new ResourceNotFoundException("List result not found for list id:"+listId+" and election id:"+election_id);
        }
    }

    public ResponseEntity<Result> getCandidateResult(@PathVariable Long election_id, @PathVariable Long candidateId) throws ResourceNotFoundException {
        Optional<Result> candidateResult = resultRepository.findByElectionIdAndCandidateId(election_id, candidateId);
        if (candidateResult.isPresent()) {
            return ResponseEntity.ok().body(candidateResult.get());
        } else {
            throw new ResourceNotFoundException("Candidate result not found for this id:"+candidateId);
        }
    }

}
