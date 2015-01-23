package agh.ddd.groups.service;

import agh.ddd.groups.poll.valueobjects.PollId;
import agh.ddd.groups.web.requestobjects.PollForm;
import com.google.common.base.Optional;
import org.joda.time.DateTime;

/**
 * @author Michał Ciołczyk
 */
public interface PollService {
    public void addPoll(PollId pollId, String content, DateTime pollDeadlineDate);
    public Optional<PollForm> getPoll(PollId pollId);
}
