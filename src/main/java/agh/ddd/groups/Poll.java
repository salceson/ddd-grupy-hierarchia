package agh.ddd.groups;

import agh.ddd.groups.events.PollCreatedEvent;
import agh.ddd.groups.valueobjects.PollId;
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

    private Poll(){}

    public Poll(PollId pollId, String content){
        apply(new PollCreatedEvent(pollId, content));
    }

    @EventSourcingHandler
    public void onPollCreated(PollCreatedEvent event){
        pollId = event.getPollId();
        content = event.getContent();
    }
}
