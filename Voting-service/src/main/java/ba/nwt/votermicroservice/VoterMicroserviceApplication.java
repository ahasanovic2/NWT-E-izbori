package ba.nwt.votermicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VoterMicroserviceApplication implements CommandLineRunner {

    @Autowired
    private VoterManagementService voterManagementService;
    public static void main(String[] args) {
        SpringApplication.run(VoterMicroserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        voterManagementService.createRows();
    }
}
