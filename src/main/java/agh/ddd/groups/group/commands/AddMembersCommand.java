package agh.ddd.groups.group.commands;

import agh.ddd.groups.group.Member;
import agh.ddd.groups.group.valueobjects.GroupId;

import java.util.Set;

public class AddMembersCommand {
    private GroupId id;
    private Set<Member> members;

    public AddMembersCommand(GroupId id, Set<Member> members) {
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
