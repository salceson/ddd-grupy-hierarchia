package agh.ddd.groups.idea;


import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import agh.ddd.groups.idea.events.IdeaProposedEvent;
import agh.ddd.groups.idea.valueobject.IdeaId;
import agh.ddd.groups.idea.valueobject.IdeaState;

public class Idea extends AbstractAnnotatedAggregateRoot {
		@AggregateIdentifier
		private IdeaId id;
		private int pollId;
		private int sectionId;
	    private String title;
	    private String description;
	    private String author;
	    private IdeaState state;

	    private Idea() {
	    }

	    public Idea(IdeaId ideaId, int sectionId, String title, String description, String author) {
	        apply(new IdeaProposedEvent(ideaId, sectionId, title, description, author));
	    }
	    
	    @EventSourcingHandler
	    public void onIdeaProposed(IdeaProposedEvent event) {
	        this.id = event.getId();
	        this.sectionId = event.getSectionId();
	        this.title = event.getTitle();
	        this.description = event.getDescription();
	        this.author = event.getAuthor();
	        this.state = IdeaState.PROPOSED;
	    }
}
