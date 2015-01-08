package agh.ddd.groups.poll;

import agh.ddd.groups.poll.valueobjects.PollId;
import agh.ddd.groups.poll.valueobjects.PollState;

/**
 * @author Michał Ciołczyk
 */
public class PollFactory {
    public static Poll create(PollId pollId, String content, PollState pollState) {
        return new Poll(pollId, content, pollState);
    }
}
