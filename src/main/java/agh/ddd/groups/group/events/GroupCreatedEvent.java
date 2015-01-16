package agh.ddd.groups.group.events;

import agh.ddd.groups.group.Member;
import agh.ddd.groups.group.valueobjects.GroupConfiguration;
import agh.ddd.groups.group.valueobjects.GroupId;

import java.util.Set;

/**
 * Created by mikolaj on 09.01.15.
 */
public class GroupCreatedEvent {
    private final GroupId groupId;
    private final GroupConfiguration configuration;
    private final String name;

    public GroupCreatedEvent(GroupId groupId, GroupConfiguration configuration, String name) {
        this.groupId = groupId;
        this.configuration = configuration;
        this.name = name;
    }

    public GroupId getGroupId() {
        return groupId;
    }

    public GroupConfiguration getConfiguration() {
        return configuration;
    }

    public String getName() {
        return name;
    }
}
