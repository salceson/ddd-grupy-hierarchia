package agh.ddd.groups.poll.sagas;

import agh.ddd.groups.poll.commands.PollDeadlineReachedCommand;
import agh.ddd.groups.poll.events.PollCreatedEvent;
import agh.ddd.groups.poll.events.PollFinishedEvent;
import agh.ddd.groups.poll.events.PollProlongedEvent;
import com.google.common.base.Optional;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.eventhandling.scheduling.ScheduleToken;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author Michał Ciołczyk
 */
public class PollDeadlineSaga extends AbstractAnnotatedSaga implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient EventScheduler eventScheduler;
    private transient CommandGateway commandGateway;
    private Optional<ScheduleToken> scheduleTokenOptional = Optional.absent();

    @Autowired
    public void setEventScheduler(EventScheduler eventScheduler) {
        this.eventScheduler = eventScheduler;
    }

    @Autowired
    public void setCommandGateway(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "pollId")
    public void handle(final PollCreatedEvent pollCreatedEvent){
        if (scheduleTokenOptional.isPresent()) {
            final ScheduleToken scheduleToken = scheduleTokenOptional.get();
            eventScheduler.cancelSchedule(scheduleToken);
        }

        final ScheduleToken scheduledToken = eventScheduler.schedule(
                pollCreatedEvent.getPollDeadlineDate(),
                new SagaDeadlineReachedEvent(pollCreatedEvent.getPollId())
        );

        scheduleTokenOptional = Optional.of(scheduledToken);
    }

    @SagaEventHandler(associationProperty = "pollId")
    public void handle(final PollFinishedEvent pollFinishedEvent){
        if (scheduleTokenOptional.isPresent()) {
            final ScheduleToken scheduleToken = scheduleTokenOptional.get();
            eventScheduler.cancelSchedule(scheduleToken);

            scheduleTokenOptional = Optional.absent();
        }

        end();
    }

    @SagaEventHandler(associationProperty = "pollId")
    public void handle(final SagaDeadlineReachedEvent sagaDeadlineReachedEvent){
        commandGateway.send(new PollDeadlineReachedCommand(sagaDeadlineReachedEvent.getPollId()));
        end();
    }

    @SagaEventHandler(associationProperty = "pollId")
    public void handle(final PollProlongedEvent pollProlongedEvent){
        if (scheduleTokenOptional.isPresent()) {
            final ScheduleToken scheduleToken = scheduleTokenOptional.get();
            eventScheduler.cancelSchedule(scheduleToken);
        }

        final ScheduleToken scheduledToken = eventScheduler.schedule(
                pollProlongedEvent.getNewPollDeadlineDate(),
                new SagaDeadlineReachedEvent(pollProlongedEvent.getPollId())
        );

        scheduleTokenOptional = Optional.of(scheduledToken);
    }
}
