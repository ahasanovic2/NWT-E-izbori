package ba.nwt.electionmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class ElectionManagementApplication implements CommandLineRunner {

    @Autowired
    private ElectionManagementService electionManagementService;

    public static void main(String[] args) {
        SpringApplication.run(ElectionManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        electionManagementService.createRows();
    }
}
