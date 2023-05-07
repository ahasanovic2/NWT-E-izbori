package ba.nwt.tim3.usermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

@RefreshScope
@RestController
class MessageRestController {

    @Value("${message:Hello default}")
    private String message;

    @RequestMapping("/message")
    String getMessage() {
        return this.message;
    }
}
