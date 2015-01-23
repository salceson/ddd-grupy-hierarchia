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
    private final String ideaDescription;
    private final DateTime pollDeadlineDate;

    public CreatePollCommand(PollId pollId, String ideaDescription, DateTime pollDeadlineDate) {
        this.pollId = pollId;
        this.ideaDescription = ideaDescription;
        this.pollDeadlineDate = pollDeadlineDate;
    }

    public PollId getPollId() {
        return pollId;
    }

    public String getIdeaDescription() {
        return ideaDescription;
    }

    public DateTime getPollDeadlineDate() {
        return pollDeadlineDate;
    }
}
