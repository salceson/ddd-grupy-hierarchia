package agh.ddd.groups.group.events;

import agh.ddd.groups.group.valueobjects.GroupId;

public class GroupSplitEvent {
    private final GroupId oldGroupId;
    private final GroupId newGroupId;

    public GroupSplitEvent(GroupId oldGroupId, GroupId newGroupId) {

        this.oldGroupId = oldGroupId;
        this.newGroupId = newGroupId;
    }
}
