package agh.ddd.groups.group.events;

import agh.ddd.groups.group.Member;
import agh.ddd.groups.group.valueobjects.GroupConfiguration;
import agh.ddd.groups.group.valueobjects.GroupId;

import java.util.Set;

public class GroupSplitStartedEvent {
    private GroupId groupId;
    private GroupId newGroupId;
    private String newGroupName;
    private Set<Member> movedMembers;
    private GroupConfiguration configuration;

    public GroupSplitStartedEvent(GroupId oldGroupId, GroupId newGroupId, String newGroupName, Set<Member> movedMembers, GroupConfiguration configuration) {
        this.groupId = oldGroupId;
        this.newGroupId = newGroupId;
        this.newGroupName = newGroupName;
        this.movedMembers = movedMembers;
        this.configuration = configuration;
    }

    public GroupId getGroupId() {
        return groupId;
    }

    public GroupId getNewGroupId() {
        return newGroupId;
    }

    public Set<Member> getMovedMembers() {
        return movedMembers;
    }

    public String getNewGroupName() {
        return newGroupName;
    }

    public GroupConfiguration getConfiguration() {
        return configuration;
    }
}
