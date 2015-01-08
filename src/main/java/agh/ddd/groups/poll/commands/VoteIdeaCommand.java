package agh.ddd.groups.poll.commands;

import agh.ddd.groups.poll.valueobjects.PollId;
import agh.ddd.groups.poll.valueobjects.UserId;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * @author Michał Ciołczyk
 */
public class VoteIdeaCommand {
    @TargetAggregateIdentifier
    private final PollId pollId;
    private final UserId userId;

    public VoteIdeaCommand(PollId pollId, UserId userId) {
        this.pollId = pollId;
        this.userId = userId;
    }

    public PollId getPollId() {
        return pollId;
    }

    public UserId getUserId() {
        return userId;
    }
}
