package agh.ddd.groups.poll.events;

import agh.ddd.groups.poll.valueobjects.PollId;
import agh.ddd.groups.poll.valueobjects.UserId;

/**
 * @author Michał Ciołczyk
 */
public class MemberInterestedEvent {
    private final UserId userId;
    private final PollId pollId;

    public MemberInterestedEvent(PollId pollId, UserId userId) {
        this.userId = userId;
        this.pollId = pollId;
    }

    public UserId getUserId() {
        return userId;
    }

    public PollId getPollId() {
        return pollId;
    }
}
