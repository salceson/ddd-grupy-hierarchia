package agh.ddd.groups.poll.events;

import agh.ddd.groups.poll.valueobjects.PollId;
import org.joda.time.DateTime;

/**
 * @author Michał Ciołczyk
 */
public class PollCreatedEvent {
    private final PollId pollId;
    private final String ideaDescription;
    private final DateTime pollDeadlineDate;

    public PollCreatedEvent(PollId pollId, String ideaDescription, DateTime pollDeadlineDate) {
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
