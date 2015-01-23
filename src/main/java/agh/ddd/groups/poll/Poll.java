package agh.ddd.groups.poll;

import agh.ddd.groups.poll.events.*;
import agh.ddd.groups.poll.valueobjects.PollId;
import agh.ddd.groups.poll.valueobjects.PollState;
import agh.ddd.groups.poll.valueobjects.UserId;
import com.google.common.base.Preconditions;
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
    private String ideaDescription;
    private PollState pollState;
    private DateTime pollDeadlineDate;
    private Set<UserId> userVotes = new HashSet<UserId>();

    private Poll() {}

    public Poll(PollId pollId, String ideaDescription, DateTime pollDeadlineDate){
        Preconditions.checkNotNull(pollId);
        Preconditions.checkNotNull(ideaDescription);
        Preconditions.checkNotNull(pollDeadlineDate);
        Preconditions.checkArgument(!pollDeadlineDate.isBeforeNow());
        apply(new PollCreatedEvent(pollId, ideaDescription, pollDeadlineDate));
    }

    public void prolong(DateTime newDeadlineDate, UserId userId) {
        if(newDeadlineDate.isBeforeNow()) {
            throw new IllegalArgumentException("Cannot prolong poll to past date!");
        }

        apply(new PollProlongedEvent(pollId, newDeadlineDate));
    }

    public void finishPoll(UserId userId) {
        if(pollState == PollState.FINISHED) {
            throw new IllegalStateException("Poll already finished!");
        }

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

    public void deadlineReached(){
        if(pollState != PollState.OPENED){
            throw new IllegalStateException("Deadline can be reached only when poll is opened!");
        }

        apply(new PollDeadlineReachedEvent(pollId));
    }

    @EventSourcingHandler
    public void onPollCreated(PollCreatedEvent event) {
        pollId = event.getPollId();
        ideaDescription = event.getIdeaDescription();
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

    @EventSourcingHandler
    public void onPollDeadlineReached(PollDeadlineReachedEvent event){
        pollState = PollState.DEADLINE_PASSED;
    }

    public PollId getPollId() {
        return pollId;
    }

    public String getIdeaDescription() {
        return ideaDescription;
    }

    public DateTime getPollDeadlineDate() {
        return pollDeadlineDate;
    }

    public PollState getPollState() {
        return pollState;
    }

    public Set<UserId> getUserVotes() {
        return userVotes;
    }
}
