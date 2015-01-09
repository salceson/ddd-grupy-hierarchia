package agh.ddd.groups.group.events;

import agh.ddd.groups.group.valueobjects.GroupId;

public class GroupCreatedEvent {
    private final GroupId id;
    private final String name;

    public GroupCreatedEvent(GroupId id, String name) {
        this.id = id;
        this.name = name;
    }

    public GroupId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
