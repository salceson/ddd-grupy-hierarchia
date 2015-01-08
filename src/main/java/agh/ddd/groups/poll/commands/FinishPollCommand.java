package agh.ddd.groups.poll.commands;

import agh.ddd.groups.poll.valueobjects.PollId;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * @author Michal Janczykowski
 */
public class FinishPollCommand {

    @TargetAggregateIdentifier
    private final PollId pollId;

    public FinishPollCommand(PollId pollId) {
        this.pollId = pollId;
    }

    public PollId getPollId() {
        return pollId;
    }
}
