package ba.nwt.tim3.systemevents;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
@SpringBootApplication
public class GrpcServer implements CommandLineRunner {

    @Autowired
    ApplicationContext applicationContext;
    public static void main (String[] args) {
        SpringApplication.run(GrpcServer.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        Server server = ServerBuilder.forPort(8800).addService(new EventImpl(applicationContext)).build();
        try {
            server.start();
            System.out.println("Server started");
            server.awaitTermination();
        }
        catch (IOException e) {
            System.out.println("All good");
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            System.out.println("No worries");
            e.printStackTrace();
        }
    }

}
