package agh.ddd.groups.poll.commands;

import agh.ddd.groups.poll.valueobjects.PollId;

/**
 * @author Michał Ciołczyk
 */
public class PollDeadlineReachedCommand {
    private final PollId pollId;

    public PollDeadlineReachedCommand(PollId pollId) {
        this.pollId = pollId;
    }

    public PollId getPollId() {
        return pollId;
    }
}
