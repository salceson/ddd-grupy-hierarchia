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

    public GroupCreatedEvent(GroupId groupId,  GroupConfiguration configuration) {
        this.groupId = groupId;
        this.configuration = configuration;
    }

    public GroupId getGroupId() {
        return groupId;
    }

    public GroupConfiguration getConfiguration() {
        return configuration;
    }
}
