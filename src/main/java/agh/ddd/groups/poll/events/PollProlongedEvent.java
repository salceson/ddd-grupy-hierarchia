package agh.ddd.groups.poll.events;

import agh.ddd.groups.poll.valueobjects.PollId;
import org.joda.time.DateTime;

/**
 * @author Michal Janczykowski
 */
public class PollProlongedEvent {
    private final PollId pollId;
    private final DateTime newPollDeadlineDate;

    public PollProlongedEvent(PollId pollId, DateTime newPollDeadlineDate) {
        this.pollId = pollId;
        this.newPollDeadlineDate = newPollDeadlineDate;
    }

    public PollId getPollId() {
        return pollId;
    }

    public DateTime getNewPollDeadlineDate() {
        return newPollDeadlineDate;
    }
}
