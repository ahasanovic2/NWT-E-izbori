package ba.nwt.tim3.notificationmicroservices.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
