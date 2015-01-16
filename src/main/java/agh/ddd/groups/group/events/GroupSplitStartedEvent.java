package agh.ddd.groups.group.events;

import agh.ddd.groups.group.Member;
import agh.ddd.groups.group.valueobjects.GroupConfiguration;
import agh.ddd.groups.group.valueobjects.GroupId;
import com.google.common.collect.ImmutableSet;

import java.util.Set;

public class GroupSplitStartedEvent {
    private final GroupId groupId;
    private final GroupId newGroupId;
    private final String newGroupName;
    private final Set<Member> movedMembers;
    private final GroupConfiguration configuration;

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
        return ImmutableSet.copyOf(movedMembers);
    }

    public String getNewGroupName() {
        return newGroupName;
    }

    public GroupConfiguration getConfiguration() {
        return configuration;
    }
}
