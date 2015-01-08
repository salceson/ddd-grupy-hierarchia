package agh.ddd.groups.poll;

import agh.ddd.groups.poll.commands.CreatePollCommand;
import agh.ddd.groups.poll.events.PollCreatedEvent;
import agh.ddd.groups.poll.valueobjects.PollId;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class PollTest {
    private FixtureConfiguration fixture;
    private PollId pollId = PollId.of(13L);
    private String pollContent = "Testowa ankieta";
    private DateTime pollDeadlineDate = DateTime.now();

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
                        new CreatePollCommand(pollId, pollContent, pollDeadlineDate)
                )
                .expectEvents(
                        new PollCreatedEvent(pollId, pollContent, pollDeadlineDate)
                );
    }
}