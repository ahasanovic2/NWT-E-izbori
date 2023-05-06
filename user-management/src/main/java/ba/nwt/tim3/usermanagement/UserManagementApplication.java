package ba.nwt.tim3.usermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserManagementApplication implements CommandLineRunner {

    @Autowired
    private UserManagementService userManagementService;

    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userManagementService.createRows();
    }

}
