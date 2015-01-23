package agh.ddd.groups.poll.sagas;

import agh.ddd.groups.poll.valueobjects.PollId;

/**
 * @author Michał Ciołczyk
 */
class SagaDeadlineReachedEvent {
    private final PollId pollId;

    public SagaDeadlineReachedEvent(PollId pollId) {
        this.pollId = pollId;
    }

    public PollId getPollId() {
        return pollId;
    }
}
