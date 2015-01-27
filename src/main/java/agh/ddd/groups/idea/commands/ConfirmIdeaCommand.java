package agh.ddd.groups.idea.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import agh.ddd.groups.idea.valueobject.IdeaId;

public class ConfirmIdeaCommand {
    @TargetAggregateIdentifier
    private final IdeaId id;

    public ConfirmIdeaCommand(IdeaId id) {
        this.id = id;
    }

    public IdeaId getId() {
        return id;
    }
}
