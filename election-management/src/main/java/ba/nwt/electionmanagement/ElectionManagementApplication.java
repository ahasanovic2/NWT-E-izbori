package ba.nwt.electionmanagement;

import ba.nwt.electionmanagement.grpc.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ElectionManagementApplication implements CommandLineRunner {

    @Autowired
    private ElectionManagementService electionManagementService;

    public static void main(String[] args) {
        GrpcClient grpcClient = GrpcClient.get();
        SpringApplication.run(ElectionManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        electionManagementService.createRows();
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