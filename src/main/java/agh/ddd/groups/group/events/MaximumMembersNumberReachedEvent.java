package agh.ddd.groups.group.events;

import agh.ddd.groups.group.valueobjects.GroupId;

/**
 * Created by mikolaj on 09.01.15.
 */
public class MaximumMembersNumberReachedEvent {
    private final GroupId id;

    public MaximumMembersNumberReachedEvent(GroupId id) {
        this.id = id;
    }

    public GroupId getId() {
        return id;
    }
}
