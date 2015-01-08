package agh.ddd.groups.poll.sagas;

import agh.ddd.groups.poll.events.PollCreatedEvent;
import agh.ddd.groups.poll.events.PollDeadlineReachedEvent;
import agh.ddd.groups.poll.events.PollFinishedEvent;
import agh.ddd.groups.poll.events.PollProlongedEvent;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.eventhandling.scheduling.ScheduleToken;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author Michał Ciołczyk
 */
@Component
public class PollDeadlineSaga extends AbstractAnnotatedSaga implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient EventScheduler eventScheduler;
    private ScheduleToken scheduleToken;

    @Autowired
    public void setEventScheduler(EventScheduler eventScheduler) {
        this.eventScheduler = eventScheduler;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "pollId")
    public void handle(final PollCreatedEvent pollCreatedEvent){
        if(scheduleToken != null){
            eventScheduler.cancelSchedule(scheduleToken);
        }

        scheduleToken = eventScheduler.schedule(
                pollCreatedEvent.getPollDeadlineDate(),
                new PollDeadlineReachedEvent(pollCreatedEvent.getPollId())
        );
    }

    @SagaEventHandler(associationProperty = "pollId")
    public void handle(final PollFinishedEvent pollFinishedEvent){
        if(scheduleToken != null){
            eventScheduler.cancelSchedule(scheduleToken);
        }
        end();
    }

    @SagaEventHandler(associationProperty = "pollId")
    public void handle(final PollProlongedEvent pollProlongedEvent){
        if(scheduleToken != null){
            eventScheduler.cancelSchedule(scheduleToken);
        }
        scheduleToken = eventScheduler.schedule(
                pollProlongedEvent.getNewPollDeadlineDate(),
                new PollDeadlineReachedEvent(pollProlongedEvent.getPollId())
        );
    }
}
