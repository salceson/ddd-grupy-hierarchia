package agh.ddd.groups.idea.events;

import agh.ddd.groups.idea.valueobject.IdeaId;

public class IdeaConfirmedEvent {
    private final IdeaId id;

    public IdeaConfirmedEvent(IdeaId id) {
        this.id = id;
    }

    public IdeaId getId() {
        return id;
    }
}
