package agh.ddd.groups.poll;

import agh.ddd.groups.poll.commands.CreatePollCommand;
import agh.ddd.groups.poll.commands.FinishPollCommand;
import agh.ddd.groups.poll.commands.VoteIdeaCommand;
import agh.ddd.groups.poll.events.MemberInterestedEvent;
import agh.ddd.groups.poll.commands.ProlongPollCommand;
import agh.ddd.groups.poll.events.PollCreatedEvent;
import agh.ddd.groups.poll.events.PollDeadlineReachedEvent;
import agh.ddd.groups.poll.events.PollFinishedEvent;
import agh.ddd.groups.poll.events.PollProlongedEvent;
import agh.ddd.groups.poll.valueobjects.PollId;
import agh.ddd.groups.poll.valueobjects.PollState;
import agh.ddd.groups.poll.valueobjects.UserId;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class PollTest {
    private FixtureConfiguration fixture;

    private PollId pollId = PollId.of(13L);
    private String pollContent = "Testowa ankieta";
    private PollState pollState = PollState.OPENED;
    private UserId userId = UserId.of(7L);
    private DateTime pollDeadlineDate = DateTime.now();
    private UserId anotherUserId = UserId.of(5L);

    @Before
    public void setUp() throws Exception {
        fixture = Fixtures.newGivenWhenThenFixture(Poll.class);
        PollCommandHandler pollCommandHandler = new PollCommandHandler();
        pollCommandHandler.setEnrollmentRepository(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(pollCommandHandler);
    }

    @Test
    public void createPollCommandShouldCreateNewPoll() throws Exception{
        fixture.givenNoPriorActivity()
                .when(
                        new CreatePollCommand(pollId, pollContent, pollDeadlineDate)
                )
                .expectEvents(
                        new PollCreatedEvent(pollId, pollContent, pollDeadlineDate)
                );
    }

    @Test
    public void finishPollCommandShouldFinishPoll() throws Exception {
        fixture
                .given(
                        new PollCreatedEvent(pollId, pollContent, pollDeadlineDate)
                )
                .when(
                        new FinishPollCommand(pollId, userId)
                )
                .expectEvents(
                        new PollFinishedEvent(pollId, 0)
                );
    }

    @Test
    public void finishPollCommandShouldThrowExceptionIfPollWasAlreadyFinished() throws Exception {
        fixture
                .given(
                        new PollCreatedEvent(pollId, pollContent, pollDeadlineDate),
                        new PollFinishedEvent(pollId, 0)
                )
                .when(
                        new FinishPollCommand(pollId, userId)
                )
                .expectException(
                        IllegalStateException.class
                );
    }

    @Test
    public void finishPollCommandShouldGenerateEventWithCorrectAmountOfVotes() throws Exception {
        fixture
                .given(
                        new PollCreatedEvent(pollId, pollContent, pollDeadlineDate),
                        new MemberInterestedEvent(pollId, userId),
                        new MemberInterestedEvent(pollId, anotherUserId)
                )
                .when(
                        new FinishPollCommand(pollId, userId)
                )
                .expectEvents(
                        new PollFinishedEvent(pollId, 2)
                );
    }

    @Test
    public void prolongPollCommandShouldProlongPoll() throws Exception {
        final DateTime newDeadlineDate = new DateTime().plusDays(1);

        fixture
                .given(
                new PollCreatedEvent(pollId, pollContent, pollDeadlineDate),
                new PollDeadlineReachedEvent(pollId)
                )
                .when(
                        new ProlongPollCommand(pollId, userId, newDeadlineDate)
                )
                .expectEvents(
                        new PollProlongedEvent(pollId, newDeadlineDate)
                );
    }

    @Test
    public void prolongPollCommandShouldThrowExceptionForPastDate() throws Exception {
        final DateTime newDeadlineDate = new DateTime().minusHours(1);

        fixture
                .given(
                        new PollCreatedEvent(pollId, pollContent, pollDeadlineDate),
                        new PollDeadlineReachedEvent(pollId)
                )
                .when(
                        new ProlongPollCommand(pollId, userId, newDeadlineDate)
                )
                .expectException(
                        IllegalArgumentException.class
                );
    }

    @Test
    public void voteIdeaCommandShouldVoteForIdea() throws Exception{
        fixture
                .given(
                        new PollCreatedEvent(pollId, pollContent, pollDeadlineDate)
                )
                .when(
                        new VoteIdeaCommand(pollId, userId)
                )
                .expectEvents(
                        new MemberInterestedEvent(pollId, userId)
                );
    }

    @Test
    public void voteIdeaCommandShouldThrowExceptionIfPollWasAlreadyFinished() throws Exception {
        fixture
                .given(
                        new PollCreatedEvent(pollId, pollContent, pollDeadlineDate),
                        new PollFinishedEvent(pollId, 0)
                )
                .when(
                        new VoteIdeaCommand(pollId, userId)
                )
                .expectException(IllegalStateException.class);
    }

    @Test
    public void voteIdeaCommandShouldThrowExceptionIfVotedTwice() throws Exception {
        fixture
                .given(
                        new PollCreatedEvent(pollId, pollContent, pollDeadlineDate),
                        new MemberInterestedEvent(pollId, userId)
                )
                .when(
                        new VoteIdeaCommand(pollId, userId)
                )
                .expectException(IllegalArgumentException.class);
    }

}