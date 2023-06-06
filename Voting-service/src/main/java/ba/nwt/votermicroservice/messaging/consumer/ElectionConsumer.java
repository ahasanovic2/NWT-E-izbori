package ba.nwt.votermicroservice.messaging.consumer;
import ba.nwt.votermicroservice.messaging.RabbitConfig;
import ba.nwt.votermicroservice.models.Election;
import ba.nwt.votermicroservice.repositories.ElectionRepository;
import org.springframework.stereotype.Component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ElectionConsumer {

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "voting-queue")
    public void consumeMessageFromQueue(ElectionMessage votingMessage) {
        Election izbor = new Election();
        izbor.setId(votingMessage.getElection_id());
        izbor.setName(votingMessage.getElection_name());
        izbor.setDescription(votingMessage.getElection_description());
        izbor.setStartTime(votingMessage.getElection_startTime());
        izbor.setEndTime(votingMessage.getElection_endTime());
        izbor.setStatus(votingMessage.getElection_status());

        electionRepository.save(izbor);
        return;
    }}
