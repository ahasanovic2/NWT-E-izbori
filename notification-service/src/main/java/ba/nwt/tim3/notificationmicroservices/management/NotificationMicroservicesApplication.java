package ba.nwt.tim3.notificationmicroservices.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class NotificationMicroservicesApplication implements CommandLineRunner {

	@Autowired
	private NotificationManagementService notificationManagementService;

	public static void main(String[] args) {

		SpringApplication.run(NotificationMicroservicesApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		notificationManagementService.createRows();
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