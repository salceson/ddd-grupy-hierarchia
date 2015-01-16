package agh.ddd.groups.group.commands;

import agh.ddd.groups.group.Member;
import agh.ddd.groups.group.valueobjects.GroupId;
import com.google.common.collect.ImmutableSet;

import java.util.Set;

public class AddMembersCommand {
    private final GroupId id;
    private final Set<Member> members;

    public AddMembersCommand(GroupId id, Set<Member> members) {
        this.id = id;
        this.members = members;
    }

    public GroupId getId() {
        return id;
    }

    public Set<Member> getMembers() {
        return ImmutableSet.copyOf(members);
    }
}
