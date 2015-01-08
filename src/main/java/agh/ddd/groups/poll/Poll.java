package agh.ddd.groups.poll;

import agh.ddd.groups.poll.events.PollCreatedEvent;
import agh.ddd.groups.poll.events.PollFinishedEvent;
import agh.ddd.groups.poll.valueobjects.PollId;
import agh.ddd.groups.poll.valueobjects.PollState;
import agh.ddd.groups.poll.valueobjects.UserId;
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

    public void finishPoll(UserId userId) {
        if(pollState == PollState.FINISHED) {
            throw new IllegalStateException("Poll already finished!");
        }

        //TODO check user privileges here?

        //TODO set votes count to real value
        final long votes = 0;

        apply(new PollFinishedEvent(pollId, votes));
    }

    @EventSourcingHandler
    public void onPollCreated(PollCreatedEvent event){
        pollId = event.getPollId();
        content = event.getContent();
        pollState = event.getPollState();
    }

    @EventSourcingHandler
    public void onPollFinished(PollFinishedEvent event) {
        pollState = PollState.FINISHED;
    }
}
