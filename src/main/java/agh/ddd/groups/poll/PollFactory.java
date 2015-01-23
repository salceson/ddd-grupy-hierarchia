package agh.ddd.groups.poll;

import agh.ddd.groups.poll.valueobjects.PollId;
import org.joda.time.DateTime;

/**
 * @author Michał Ciołczyk
 */
public class PollFactory {
    public static Poll create(PollId pollId, String ideaDescription, DateTime pollDeadlineDate) {
        return new Poll(pollId, ideaDescription, pollDeadlineDate);
    }
}
