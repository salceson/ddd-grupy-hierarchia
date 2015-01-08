package agh.ddd.groups.poll.commands;

import agh.ddd.groups.poll.valueobjects.PollId;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.DateTime;

/**
 * @author Michał Ciołczyk
 */
public class CreatePollCommand {
    @TargetAggregateIdentifier
    private final PollId pollId;
    private final String content;
    private final DateTime pollDeadlineDate;

    public CreatePollCommand(PollId pollId, String content, DateTime pollDeadlineDate) {
        this.pollId = pollId;
        this.content = content;
        this.pollDeadlineDate = pollDeadlineDate;
    }

    public PollId getPollId() {
        return pollId;
    }

    public String getContent() {
        return content;
    }

    public DateTime getPollDeadlineDate() {
        return pollDeadlineDate;
    }
}
