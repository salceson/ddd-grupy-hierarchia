package agh.ddd.groups.group.commands;

import agh.ddd.groups.group.valueobjects.GroupConfiguration;
import agh.ddd.groups.group.valueobjects.GroupId;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class CreateGroupCommand {
    @TargetAggregateIdentifier
    private final GroupId groupId;
    private final GroupConfiguration configuration;
    private final String name;

    public CreateGroupCommand(GroupId groupId, GroupConfiguration configuration, String name) {
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
