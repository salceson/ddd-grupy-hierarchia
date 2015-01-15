package agh.ddd.groups.idea.events;

import agh.ddd.groups.idea.valueobject.IdeaId;

public class IdeaPublishedEvent {
    private IdeaId id;

    public IdeaPublishedEvent(IdeaId id) {
        this.id = id;
    }

    public IdeaId getId() {
        return id;
    }
}
