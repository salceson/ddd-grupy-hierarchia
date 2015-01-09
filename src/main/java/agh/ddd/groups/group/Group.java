package agh.ddd.groups.group;

import agh.ddd.groups.group.events.GroupClosedEvent;
import agh.ddd.groups.group.events.GroupCreatedEvent;
import agh.ddd.groups.group.exceptions.GroupAlreadyClosedException;
import agh.ddd.groups.group.valueobjects.GroupId;
import com.google.common.base.Preconditions;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import static agh.ddd.groups.group.GroupState.CLOSED;
import static agh.ddd.groups.group.GroupState.OPENED;

public class Group extends AbstractAnnotatedAggregateRoot {
    @AggregateIdentifier
    private GroupId id;
    private String name;
    private GroupState state;

    private Group() {
        //only for Axon purpose
    }

    public Group(GroupId id, String name) {
        Preconditions.checkNotNull(id);
        Preconditions.checkNotNull(name);
        apply(new GroupCreatedEvent(id, name));
    }

    public void close() {
        if (state == CLOSED) {
            throw new GroupAlreadyClosedException(id);
        }
        apply(new GroupClosedEvent(id));
    }


    @EventSourcingHandler
    public void onGroupCreatedEvent(GroupCreatedEvent event) {
        id = event.getId();
        name = event.getName();
        state = OPENED;
    }

    @EventSourcingHandler
    public void onGroupClosedEvent(GroupClosedEvent event) {
        state = CLOSED;
    }

}
