package agh.ddd.groups.group.commands;

import agh.ddd.groups.group.Member;
import agh.ddd.groups.group.valueobjects.GroupId;

import java.util.Set;

public class RemoveMembersCommand {
    private GroupId id;
    private Set<Member> members;

    public RemoveMembersCommand(GroupId id, Set<Member> members) {
        this.id = id;
        this.members = members;
    }

    public GroupId getId() {
        return id;
    }

    public Set<Member> getMembers() {
        return members;
    }
}
