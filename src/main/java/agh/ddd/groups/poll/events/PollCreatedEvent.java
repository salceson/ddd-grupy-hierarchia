package agh.ddd.groups.poll.events;

import agh.ddd.groups.poll.valueobjects.PollId;
import org.joda.time.DateTime;

/**
 * @author Michał Ciołczyk
 */
public class PollCreatedEvent {
    private final PollId pollId;
    private final String content;
    private final DateTime pollDeadlineDate;

    public PollCreatedEvent(PollId pollId, String content, DateTime pollDeadlineDate) {
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
