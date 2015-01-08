package agh.ddd.groups.poll;

import agh.ddd.groups.poll.commands.CreatePollCommand;
import agh.ddd.groups.poll.commands.FinishPollCommand;
import agh.ddd.groups.poll.events.PollCreatedEvent;
import agh.ddd.groups.poll.events.PollFinishedEvent;
import agh.ddd.groups.poll.valueobjects.PollId;
import agh.ddd.groups.poll.valueobjects.PollState;
import agh.ddd.groups.poll.valueobjects.UserId;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

public class PollTest {
    private FixtureConfiguration fixture;

    private PollId pollId = PollId.of(13L);
    private String pollContent = "Testowa ankieta";
    private PollState pollState = PollState.OPENED;
    private UserId userId = UserId.of(7L);

    @Before
    public void setUp() throws Exception {
        fixture = Fixtures.newGivenWhenThenFixture(Poll.class);
        PollCommandHandler pollCommandHandler = new PollCommandHandler();
        pollCommandHandler.setEnrollmentRepository(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(pollCommandHandler);
    }

    @Test
    public void createPollCommandShouldCreateNewPoll(){
        fixture.given()
                .when(
                        new CreatePollCommand(pollId, pollContent)
                )
                .expectEvents(
                        new PollCreatedEvent(pollId, pollContent, pollState)
                );
    }

    @Test
    public void finishPollCommandShouldFinishPoll() throws Exception {
        fixture
                .given(
                        new PollCreatedEvent(pollId, pollContent, pollState)
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
                        new PollCreatedEvent(pollId, pollContent, pollState),
                        new PollFinishedEvent(pollId, 0)
                )
                .when(
                        new FinishPollCommand(pollId, userId)
                )
                .expectException(IllegalStateException.class);
    }
}