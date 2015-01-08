package agh.ddd.groups.poll;

import agh.ddd.groups.poll.valueobjects.PollId;

/**
 * @author Michał Ciołczyk
 */
public class PollFactory {
    public static Poll create(PollId pollId, String content) {
        return new Poll(pollId, content);
    }
}
