package ba.nwt.tim3.systemevents;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SystemEventsApplication {
    public static void main(String[] args) {
        Server server = ServerBuilder.forPort(8800).addService(new EventImpl()).build();
        SpringApplication.run(SystemEventsApplication.class, args);
    }

}
