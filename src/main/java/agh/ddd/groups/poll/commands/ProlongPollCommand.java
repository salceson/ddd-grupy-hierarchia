package agh.ddd.groups.poll.commands;

import agh.ddd.groups.poll.valueobjects.PollId;
import agh.ddd.groups.poll.valueobjects.UserId;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.DateTime;

/**
 * @author Michal Janczykowski
 */
public class ProlongPollCommand {

    @TargetAggregateIdentifier
    private final PollId pollId;
    private final UserId userId;
    private final DateTime newDeadlineDate;

    public ProlongPollCommand(PollId pollId, UserId userId, DateTime newDeadlineDate) {
        this.pollId = pollId;
        this.userId = userId;
        this.newDeadlineDate = newDeadlineDate;
    }

    public PollId getPollId() {
        return pollId;
    }

    public UserId getUserId() {
        return userId;
    }

    public DateTime getNewDeadlineDate() {
        return newDeadlineDate;
    }
}
