package agh.ddd.groups.group.commands;

import agh.ddd.groups.group.valueobjects.GroupId;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class CloseGroupCommand {
    @TargetAggregateIdentifier
    private final GroupId id;

    public CloseGroupCommand(GroupId id) {
        this.id = id;
    }

    public GroupId getId() {
        return id;
    }
}
