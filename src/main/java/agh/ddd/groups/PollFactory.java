package agh.ddd.groups;

import agh.ddd.groups.valueobjects.PollId;

/**
 * @author Michał Ciołczyk
 */
public class PollFactory {
    public static Poll create(PollId pollId, String content) {
        return new Poll(pollId, content);
    }
}
