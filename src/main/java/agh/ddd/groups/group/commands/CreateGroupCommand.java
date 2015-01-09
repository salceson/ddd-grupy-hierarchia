package agh.ddd.groups.group.commands;

import agh.ddd.groups.group.valueobjects.GroupId;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class CreateGroupCommand {
    @TargetAggregateIdentifier
    private final GroupId id;
    private final String name;

    public CreateGroupCommand(GroupId id, String name) {
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
