package ba.nwt.electionmanagement.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.time.LocalDateTime;

public class GrpcClient {

    private static ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8800)
            .usePlaintext()
            .build();
    private static GrpcClient grpcClient = null;

    private GrpcClient(){
        channel = channel = ManagedChannelBuilder.forAddress("localhost", 8800)
                .usePlaintext()
                .build();
    }

    public static GrpcClient get(){
        if(grpcClient == null){
            grpcClient = new GrpcClient();
        }
        return grpcClient;
    }

    public static void log(Integer userId, String resource, String action, String status){
        eventGrpc.eventBlockingStub eventStub = eventGrpc.newBlockingStub(channel);
        LogRequest logRequest = LogRequest.newBuilder()
                .setTimestamp(String.valueOf(LocalDateTime.now()))
                .setResource(resource)
                .setAction(action)
                .setStatus(status)
                .setUserId(userId)
                .build();
        try {
            eventStub.log(logRequest);

        } catch (Exception e){
        }
    }
}
