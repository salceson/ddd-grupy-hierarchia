package agh.ddd.groups.poll.commands;

import agh.ddd.groups.poll.valueobjects.PollId;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * @author Michał Ciołczyk
 */
public class CreatePollCommand {
    @TargetAggregateIdentifier
    private final PollId pollId;
    private final String content;

    public CreatePollCommand(PollId pollId, String content) {
        this.pollId = pollId;
        this.content = content;
    }

    public PollId getPollId() {
        return pollId;
    }

    public String getContent() {
        return content;
    }
}