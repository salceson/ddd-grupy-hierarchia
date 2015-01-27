package agh.ddd.groups.idea.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import agh.ddd.groups.idea.valueobject.IdeaId;

public class AcceptIdeaCommand {
    @TargetAggregateIdentifier
    private final IdeaId id;

    public AcceptIdeaCommand(IdeaId id) {
        this.id = id;
    }

    public IdeaId getId() {
        return id;
    }
}
