package agh.ddd.groups.group;

import agh.ddd.groups.group.events.*;
import agh.ddd.groups.group.exceptions.GroupAlreadyClosedException;
import agh.ddd.groups.group.exceptions.MembersNotInGroupException;
import agh.ddd.groups.group.valueobjects.GroupId;
import com.google.common.base.Preconditions;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.HashSet;
import java.util.Set;

import static agh.ddd.groups.group.GroupState.CLOSED;
import static agh.ddd.groups.group.GroupState.OPENED;

public class Group extends AbstractAnnotatedAggregateRoot {
    @AggregateIdentifier
    private GroupId id;
    private String name;
    private GroupState state;
    private final Set<Member> members = new HashSet<>();

    private Group() {
        //only for Axon purpose
    }

    public Group(GroupId id, String name) {
        apply(new GroupCreatedEvent(id, name));
    }

    public void close() {
        if (state == CLOSED) {
            throw new GroupAlreadyClosedException(id);
        }
        apply(new GroupClosedEvent(id));
    }

    public void split(GroupId newGroupId, String newGroupName, Set<Member> movedMembers) {
        if (state == CLOSED) {
            throw new GroupAlreadyClosedException(id);
        }
        //this event starts the saga responsible for splitting groups
        apply(new GroupSplitStartedEvent(id, newGroupId, newGroupName, movedMembers));
    }

    public void addMembers(Set<Member> newMembers) {
        if (state == CLOSED) {
            throw new GroupAlreadyClosedException(id);
        }
        apply(new MembersAddedEvent(id, newMembers));
    }

    public void removeMembers(Set<Member> membersToRemove) {
        if (state == CLOSED) {
            throw new GroupAlreadyClosedException(id);
        }

        if (!membersToRemove.stream().allMatch(members::contains)) {
            throw new MembersNotInGroupException();
        }
        apply(new MembersRemovedEvent(id, membersToRemove));
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

    @EventSourcingHandler
    public void onAddMembersEvent(MembersAddedEvent event) {
        members.addAll(event.getMembers());
    }

    @EventSourcingHandler
    public void onRemoveMembersEvent(MembersRemovedEvent event) {
        members.removeAll(event.getMembersToRemove());
    }
}
