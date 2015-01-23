package agh.ddd.groups.poll.events;

import agh.ddd.groups.poll.valueobjects.PollId;

/**
 * @author Michał Ciołczyk
 */
public class PollDeadlineReachedEvent {
    private final PollId pollId;

    public PollDeadlineReachedEvent(PollId pollId) {
        this.pollId = pollId;
    }

    public PollId getPollId() {
        return pollId;
    }
}
