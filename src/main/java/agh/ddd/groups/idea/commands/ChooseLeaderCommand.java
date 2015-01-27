package agh.ddd.groups.idea.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import agh.ddd.groups.idea.valueobject.IdeaId;

public class ChooseLeaderCommand {
    @TargetAggregateIdentifier
    private final IdeaId ideaId;
    private final int leaderUserId;

    public ChooseLeaderCommand(IdeaId ideaId, int leaderUserId) {
        this.ideaId = ideaId;
        this.leaderUserId = leaderUserId;
    }

    public IdeaId getIdeaId() {
        return ideaId;
    }

    public int getLeaderUserId() {
        return leaderUserId;
    }
}
