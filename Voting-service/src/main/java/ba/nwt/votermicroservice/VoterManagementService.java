package ba.nwt.votermicroservice;

import ba.nwt.votermicroservice.repositories.*;
import ba.nwt.votermicroservice.models.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VoterManagementService {

    @Transactional
    public void createRows() {
    }
}
