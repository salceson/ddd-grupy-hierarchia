package agh.ddd.groups.poll.sagas;

import agh.ddd.groups.poll.commands.PollDeadlineReachedCommand;
import agh.ddd.groups.poll.events.PollCreatedEvent;
import agh.ddd.groups.poll.events.PollDeadlineReachedEvent;
import agh.ddd.groups.poll.events.PollFinishedEvent;
import agh.ddd.groups.poll.events.PollProlongedEvent;
import agh.ddd.groups.poll.valueobjects.PollId;
import org.axonframework.test.saga.AnnotatedSagaTestFixture;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.Before;
import org.junit.Test;

public class PollDeadlineSagaTest {
    private AnnotatedSagaTestFixture fixture;

    private PollId pollId = PollId.of(5L);
    private String content = "Test poll";
    private DateTime pollDeadlineDate, prolongedDeadlineDate;

    @Before
    public void setUp() throws Exception {
        fixture = new AnnotatedSagaTestFixture(PollDeadlineSaga.class);
        DateTime now = DateTime.now();
        pollDeadlineDate = now.plusDays(14);
        prolongedDeadlineDate = now.plusDays(21);
    }

    @Test
    public void shouldPollReceiveEventWhenDeadlinePass() throws Exception {
        fixture
                .givenAggregate(pollId)
                .published(new PollCreatedEvent(pollId, content, pollDeadlineDate))
                .whenTimeElapses(Duration.standardDays(14).plus(Duration.standardSeconds(1)))
                .expectDispatchedCommandsEqualTo(new PollDeadlineReachedCommand(pollId));
    }

    @Test
    public void shouldPollReceiveNoEventsWhenFinishedAndThenDeadlinePass() throws Exception {
        fixture
                .givenAggregate(pollId)
                .published(
                        new PollCreatedEvent(pollId, content, pollDeadlineDate),
                        new PollFinishedEvent(pollId, 0)
                )
                .whenTimeElapses(Duration.standardDays(14))
                .expectNoDispatchedCommands();
    }

    @Test
    public void shouldPollReceiveDeadLineReachedEventWhenDeadlineIsProlongedAtNewDeadline() throws Exception {
        fixture
                .givenAggregate(pollId)
                .published(
                        new PollCreatedEvent(pollId, content, pollDeadlineDate),
                        new PollProlongedEvent(pollId, prolongedDeadlineDate)
                )
                .whenTimeElapses(Duration.standardDays(21))
                .expectDispatchedCommandsEqualTo(new PollDeadlineReachedCommand(pollId));
    }

    @Test
    public void shouldPollReceiveNoEventsWhenProlongedAndThenFinishedAndThePreviousDeadlinePass() throws Exception {
        fixture
                .givenAggregate(pollId)
                .published(
                        new PollCreatedEvent(pollId, content, pollDeadlineDate),
                        new PollProlongedEvent(pollId, prolongedDeadlineDate),
                        new PollFinishedEvent(pollId, 0)
                )
                .whenTimeElapses(Duration.standardDays(14))
                .expectNoDispatchedCommands();
    }

    @Test
    public void shouldPollReceiveNoEventsWhenProlongedAndThenFinishedAndTheNewDeadlinePass() throws Exception {
        fixture
                .givenAggregate(pollId)
                .published(
                        new PollCreatedEvent(pollId, content, pollDeadlineDate),
                        new PollProlongedEvent(pollId, prolongedDeadlineDate),
                        new PollFinishedEvent(pollId, 0)
                )
                .whenTimeElapses(Duration.standardDays(21))
                .expectNoDispatchedCommands();
    }

}