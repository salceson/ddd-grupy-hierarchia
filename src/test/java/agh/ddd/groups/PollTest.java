package agh.ddd.groups;

import agh.ddd.groups.commands.CreatePollCommand;
import agh.ddd.groups.events.PollCreatedEvent;
import agh.ddd.groups.valueobjects.PollId;
import org.axonframework.test.Fixtures;
import org.axonframework.test.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

public class PollTest {
    private FixtureConfiguration fixture;
    private PollId pollId = PollId.of(13L);
    private String pollContent = "Testowa ankieta";

    @Before
    public void setUp() throws Exception {
        fixture = Fixtures.newGivenWhenThenFixture(Poll.class);
        PollCommandHandler pollCommandHandler = new PollCommandHandler();
        pollCommandHandler.setEnrollmentRepository(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(pollCommandHandler);
    }

    @Test
    public void createPollShouldCreateNewPoll(){
        fixture.given()
                .when(
                        new CreatePollCommand(pollId, pollContent)
                ).expectEvents(
                new PollCreatedEvent(pollId, pollContent)
        );
    }
}