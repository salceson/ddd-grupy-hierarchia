package agh.ddd.groups.idea.events;

import agh.ddd.groups.idea.valueobject.IdeaId;

public class LeaderChosenEvent {
    private IdeaId id;
    private int leaderUserId;

    public LeaderChosenEvent(IdeaId id, int leaderUserId) {
        this.id = id;
        this.leaderUserId = leaderUserId;
    }

    public IdeaId getId() {
        return id;
    }

    public int getLeaderUserId() {
        return leaderUserId;
    }
}
