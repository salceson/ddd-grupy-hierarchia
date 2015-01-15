package agh.ddd.groups.group.commands;

import agh.ddd.groups.group.Member;
import agh.ddd.groups.group.valueobjects.GroupId;

import java.util.Set;

public class SplitGroupCommand {
    private final GroupId oldGroupId;
    private final GroupId newGroupId;
    private final String newGroupName;
    private final Set<Member> movedMemberSet;

    public SplitGroupCommand(GroupId oldGroupId, GroupId newGroupId, String newGroupName, Set<Member> movedMemberSet) {
        this.oldGroupId = oldGroupId;
        this.newGroupId = newGroupId;
        this.newGroupName = newGroupName;
        this.movedMemberSet = movedMemberSet;
    }

    public GroupId getOldGroupId() {
        return oldGroupId;
    }

    public GroupId getNewGroupId() {
        return newGroupId;
    }

    public String getNewGroupName() {
        return newGroupName;
    }

    public Set<Member> getMovedMembers() {
        return movedMemberSet;
    }
}
