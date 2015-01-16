package agh.ddd.groups.group.events;

import agh.ddd.groups.group.Member;
import agh.ddd.groups.group.valueobjects.GroupId;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.Set;

public class MembersAddedEvent {
    private final GroupId id;
    private final Set<Member> members;

    public MembersAddedEvent(GroupId id, Set<Member> members) {
        this.id = id;
        this.members = members;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public GroupId getId() {
        return id;
    }
}
