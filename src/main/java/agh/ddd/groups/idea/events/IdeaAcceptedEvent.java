package agh.ddd.groups.idea.events;

import agh.ddd.groups.idea.valueobject.IdeaId;

public class IdeaAcceptedEvent {
	private IdeaId id;

	public IdeaAcceptedEvent(IdeaId id) {
		this.id = id;
	}

	public IdeaId getId() {
		return id;
	}
}
