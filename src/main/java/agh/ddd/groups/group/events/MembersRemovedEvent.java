package agh.ddd.groups.group.events;

import agh.ddd.groups.group.Member;
import agh.ddd.groups.group.valueobjects.GroupId;

import java.util.Collection;
import java.util.Set;

public class MembersRemovedEvent {
    private final GroupId id;
    private final Set<Member> membersToRemove;

    public MembersRemovedEvent(GroupId id, Set<Member> membersToRemove) {
        this.id = id;
        this.membersToRemove = membersToRemove;
    }

    public Set<Member> getMembersToRemove() {
        return membersToRemove;
    }

    public GroupId getId() {
        return id;
    }
}
