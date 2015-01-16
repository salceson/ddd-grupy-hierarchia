package agh.ddd.groups.group.events;

import agh.ddd.groups.group.valueobjects.GroupId;

public class GroupClosedEvent {
    private final GroupId id;

    public GroupClosedEvent(GroupId id) {
        this.id = id;
    }
}
