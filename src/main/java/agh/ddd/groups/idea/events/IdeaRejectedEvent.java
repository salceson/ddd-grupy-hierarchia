package agh.ddd.groups.idea.events;

import agh.ddd.groups.idea.valueobject.IdeaId;

public class IdeaRejectedEvent {
    private IdeaId id;

    public IdeaRejectedEvent(IdeaId id) {
        this.id = id;
    }

    public IdeaId getId() {
        return id;
    }
}
