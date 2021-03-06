package agh.ddd.groups.poll;

import agh.ddd.groups.poll.commands.*;
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
    public void setPollRepository(Repository<Poll> pollRepository) {
        this.pollRepository = pollRepository;
    }

    @CommandHandler
    public void handleCreatePollCommand(CreatePollCommand createPollCommand){
        Poll poll = PollFactory.create(createPollCommand.getPollId(), createPollCommand.getIdeaDescription(),
                createPollCommand.getPollDeadlineDate());
        pollRepository.add(poll);
    }

    @CommandHandler
    public void handleFinishPollCommand(FinishPollCommand finishPollCommand) {
        final Poll poll = pollRepository.load(finishPollCommand.getPollId());
        poll.finishPoll(finishPollCommand.getUserId());
    }

    @CommandHandler
    public void handleProlongPollCommand(ProlongPollCommand prolongPollCommand) {
        final Poll poll = pollRepository.load(prolongPollCommand.getPollId());
        poll.prolong(prolongPollCommand.getNewDeadlineDate(), prolongPollCommand.getUserId());
    }

    @CommandHandler
    public void handleVoteIdeaCommand(VoteIdeaCommand voteIdeaCommand){
        Poll poll = pollRepository.load(voteIdeaCommand.getPollId());
        poll.vote(voteIdeaCommand.getUserId());
    }

    @CommandHandler
    public void handlePollDeadlineReachedCommand(PollDeadlineReachedCommand pollDeadlineReachedCommand){
        Poll poll = pollRepository.load(pollDeadlineReachedCommand.getPollId());
        poll.deadlineReached();
    }
}
