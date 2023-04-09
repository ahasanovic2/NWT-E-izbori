package ba.nwt.tim3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ResultApplication implements CommandLineRunner {

	@Autowired
	private ResultService resultService;
	public static void main(String[] args) {
		SpringApplication.run(ResultApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		resultService.createRows();
	}
}
