package agh.ddd.groups.service;

import agh.ddd.groups.poll.commands.CreatePollCommand;
import agh.ddd.groups.poll.valueobjects.PollId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Michał Ciołczyk
 */
@Service
public class PollServiceImpl implements PollService {
    @Autowired
    private CommandGateway commandGateway;

    @Override
    public void addPoll(PollId pollId, String content, DateTime pollDeadlineDate) {
        commandGateway.send(new CreatePollCommand(pollId, content, pollDeadlineDate));
    }
}
