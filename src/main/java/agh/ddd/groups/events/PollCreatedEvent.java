package agh.ddd.groups.events;

import agh.ddd.groups.valueobjects.PollId;

/**
 * @author Michał Ciołczyk
 */
public class PollCreatedEvent {
    private PollId pollId;
    private String content;

    public PollCreatedEvent(PollId pollId, String content) {
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
