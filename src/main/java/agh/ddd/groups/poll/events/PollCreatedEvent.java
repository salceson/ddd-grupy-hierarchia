package agh.ddd.groups.poll.events;

import agh.ddd.groups.poll.valueobjects.PollId;
import agh.ddd.groups.poll.valueobjects.PollState;

/**
 * @author Michał Ciołczyk
 */
public class PollCreatedEvent {
    private final PollId pollId;
    private final String content;
    private final PollState pollState;

    public PollCreatedEvent(PollId pollId, String content, PollState pollState) {
        this.pollId = pollId;
        this.content = content;
        this.pollState = pollState;
    }

    public PollId getPollId() {
        return pollId;
    }

    public String getContent() {
        return content;
    }

    public PollState getPollState() {
        return pollState;
    }
}
