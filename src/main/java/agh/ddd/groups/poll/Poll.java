package agh.ddd.groups.poll;

import agh.ddd.groups.poll.events.PollCreatedEvent;
import agh.ddd.groups.poll.valueobjects.PollId;
import agh.ddd.groups.poll.valueobjects.PollState;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.DateTime;

/**
 * @author Michał Ciołczyk
 */
public class Poll extends AbstractAnnotatedAggregateRoot{
    @AggregateIdentifier
    private PollId pollId;
    private String content; //The idea is described here.
    private PollState pollState;
    private DateTime pollDeadlineDate;

    private Poll(){}

    public Poll(PollId pollId, String content, DateTime pollDeadlineDate){
        apply(new PollCreatedEvent(pollId, content, pollDeadlineDate));
    }

    @EventSourcingHandler
    public void onPollCreated(PollCreatedEvent event){
        pollId = event.getPollId();
        content = event.getContent();
        pollDeadlineDate = event.getPollDeadlineDate();
        pollState = PollState.OPENED;
    }
}
