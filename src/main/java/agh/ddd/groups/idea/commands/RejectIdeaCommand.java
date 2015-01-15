package agh.ddd.groups.idea.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import agh.ddd.groups.idea.valueobject.IdeaId;

public class RejectIdeaCommand {
    @TargetAggregateIdentifier
    private IdeaId id;

    public RejectIdeaCommand(IdeaId id) {
        this.id = id;
    }

    public IdeaId getId() {
        return id;
    }
}
