package agh.ddd.groups.group.commands;

import agh.ddd.groups.group.valueobjects.GroupConfiguration;
import agh.ddd.groups.group.valueobjects.GroupId;

/**
 * Created by mikolaj on 09.01.15.
 */
public class CreateGroupCommand {
    private final GroupId groupId;
    private final GroupConfiguration configuration;

    public CreateGroupCommand(GroupId groupId, GroupConfiguration configuration) {
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
