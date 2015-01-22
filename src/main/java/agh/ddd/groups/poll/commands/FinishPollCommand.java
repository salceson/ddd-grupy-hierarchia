package agh.ddd.groups.poll.commands;

import agh.ddd.groups.poll.valueobjects.PollId;
import agh.ddd.groups.poll.valueobjects.UserId;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * @author Michal Janczykowski
 */
public class FinishPollCommand {

    @TargetAggregateIdentifier
    private final PollId pollId;

    private final UserId userId;

    public FinishPollCommand(PollId pollId, UserId userId) {
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
