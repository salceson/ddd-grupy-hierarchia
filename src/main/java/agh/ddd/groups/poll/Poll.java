package agh.ddd.groups.poll;

import agh.ddd.groups.poll.events.MemberInterestedEvent;
import agh.ddd.groups.poll.events.PollCreatedEvent;
import agh.ddd.groups.poll.events.PollFinishedEvent;
import agh.ddd.groups.poll.events.PollProlongedEvent;
import agh.ddd.groups.poll.valueobjects.PollId;
import agh.ddd.groups.poll.valueobjects.PollState;
import agh.ddd.groups.poll.valueobjects.UserId;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.DateTime;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Michał Ciołczyk
 */
public class Poll extends AbstractAnnotatedAggregateRoot{
    @AggregateIdentifier
    private PollId pollId;
    private String content; //The idea is described here.
    private PollState pollState;
    private DateTime pollDeadlineDate;
    private Set<UserId> userVotes = new HashSet<UserId>();

    private Poll() {}

    public Poll(PollId pollId, String content, DateTime pollDeadlineDate){
        apply(new PollCreatedEvent(pollId, content, pollDeadlineDate));
    }

    public void prolong(DateTime newDeadlineDate, UserId userId) {
        //TODO check user privileges here?

        if(newDeadlineDate.isBeforeNow()) {
            throw new IllegalArgumentException("Cannot prolong poll to past date!");
        }

        apply(new PollProlongedEvent(pollId, newDeadlineDate));
    }

    public void finishPoll(UserId userId) {
        if(pollState == PollState.FINISHED) {
            throw new IllegalStateException("Poll already finished!");
        }

        //TODO check user privileges here?

        apply(new PollFinishedEvent(pollId, userVotes.size()));
    }

    public void vote(UserId userId) {
        if(pollState != PollState.OPENED) {
            throw new IllegalStateException("You can vote only if the poll is opened!");
        }

        if(userVotes.contains(userId)){
            throw new IllegalArgumentException("You can vote only once!");
        }

        apply(new MemberInterestedEvent(pollId, userId));
    }

    @EventSourcingHandler
    public void onPollCreated(PollCreatedEvent event) {
        pollId = event.getPollId();
        content = event.getContent();
        pollDeadlineDate = event.getPollDeadlineDate();
        pollState = PollState.OPENED;
    }

    @EventSourcingHandler
    public void onPollProlonged(PollProlongedEvent event) {
        pollDeadlineDate = event.getNewPollDeadlineDate();
    }

    @EventSourcingHandler
    public void onPollFinished(PollFinishedEvent event) {
        pollState = PollState.FINISHED;
    }

    @EventSourcingHandler
    public void onMemberInterested(MemberInterestedEvent event){
        userVotes.add(event.getUserId());
    }
}
