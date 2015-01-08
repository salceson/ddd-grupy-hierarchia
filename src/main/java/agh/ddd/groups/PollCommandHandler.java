package agh.ddd.groups;

import agh.ddd.groups.commands.CreatePollCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Michał Ciołczyk
 */
@Component
public class PollCommandHandler {
    private Repository<Poll> pollRepository;

    @Autowired
    @Qualifier("pollRepository")
    public void setEnrollmentRepository(Repository<Poll> pollRepository) {
        this.pollRepository = pollRepository;
    }

    @CommandHandler
    public void handleCreatePollCommand(CreatePollCommand createPollCommand){
        Poll poll = PollFactory.create(createPollCommand.getPollId(), createPollCommand.getContent());
        pollRepository.add(poll);
    }

}
