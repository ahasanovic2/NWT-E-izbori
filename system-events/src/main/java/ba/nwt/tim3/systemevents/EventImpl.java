package ba.nwt.tim3.systemevents;
import ba.nwt.tim3.systemevents.grpc.*;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@GRpcService
public class EventImpl extends eventGrpc.eventImplBase{

    ApplicationContext applicationContext;
    public EventImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Autowired
    private ActionRepository actionRepository;

    @Override
    public void log(LogRequest request, StreamObserver<APIResponse> responseObserver) {
        String message = new StringBuilder().append("Event time : ")
                .append(request.getTimestamp())
                .append(";\n").append("Resource name : ")
                .append(request.getResource())
                .append(";\n").append("Action taken : ")
                .append(request.getAction())
                .append(";\n").append("Status : ")
                .append(request.getStatus())
                .append(";\n").toString();
        actionRepository = applicationContext.getBean(ActionRepository.class);
        Action action = new Action(request.getUserId(), request.getAction(), request.getStatus(), request.getResource(), request.getTimestamp());
        actionRepository.save(action);
        System.out.println(message);
        APIResponse response = APIResponse.newBuilder()
                .setMessage("123")
                .setResponseCode(200)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        System.out.println("Request sent");
    }

}
