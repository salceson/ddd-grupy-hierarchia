package agh.ddd.groups.service;

import agh.ddd.groups.poll.valueobjects.PollId;
import org.joda.time.DateTime;

/**
 * @author Michał Ciołczyk
 */
public interface PollService {
    public void addPoll(PollId pollId, String content, DateTime pollDeadlineDate);
}
