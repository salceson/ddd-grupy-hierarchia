package agh.ddd.groups.service;

import agh.ddd.groups.poll.Poll;
import agh.ddd.groups.poll.commands.CreatePollCommand;
import agh.ddd.groups.poll.valueobjects.PollId;
import agh.ddd.groups.web.requestobjects.PollForm;
import com.google.common.base.Optional;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.repository.Repository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Michał Ciołczyk
 */
@Service
public class PollServiceImpl implements PollService {
    private final CommandGateway commandGateway;

    private final Repository<Poll> repository;

    @Autowired
    public PollServiceImpl(CommandGateway commandGateway, Repository<Poll> repository) {
        this.commandGateway = commandGateway;
        this.repository = repository;
    }

    @Override
    public void addPoll(PollId pollId, String content, DateTime pollDeadlineDate) {
        commandGateway.send(new CreatePollCommand(pollId, content, pollDeadlineDate));
    }

    @Override
    public Optional<PollForm> getPoll(PollId pollId) {
        final Poll poll =  repository.load(pollId);

        if(poll == null) {
            return Optional.absent();
        }

        final PollForm pollForm = new PollForm();
        pollForm.setContent(poll.getIdeaDescription());
        pollForm.setDeadline(poll.getPollDeadlineDate());

        return Optional.of(pollForm);
    }
}
