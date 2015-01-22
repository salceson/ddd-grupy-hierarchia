package agh.ddd.groups.poll.events;

import agh.ddd.groups.poll.valueobjects.PollId;

/**
 * @author Michał Janczykowski
 */
public class PollFinishedEvent {
    private final PollId pollId;
    private final long votes;

    public PollFinishedEvent(PollId pollId, long votes) {
        this.pollId = pollId;
        this.votes = votes;
    }

    public PollId getPollId() {
        return pollId;
    }

    public long getVotes() {
        return votes;
    }
}
