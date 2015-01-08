package agh.ddd.groups.poll;

import agh.ddd.groups.poll.events.PollCreatedEvent;
import agh.ddd.groups.poll.valueobjects.PollId;
import agh.ddd.groups.poll.valueobjects.PollState;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * @author Michał Ciołczyk
 */
public class Poll extends AbstractAnnotatedAggregateRoot{
    @AggregateIdentifier
    private PollId pollId;
    private String content;
    private PollState pollState;

    private Poll(){}

    public Poll(PollId pollId, String content, PollState pollState){
        apply(new PollCreatedEvent(pollId, content, pollState));
    }

    @EventSourcingHandler
    public void onPollCreated(PollCreatedEvent event){
        pollId = event.getPollId();
        content = event.getContent();
        pollState = event.getPollState();
    }
}
