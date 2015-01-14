package agh.ddd.groups.idea;


import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import agh.ddd.groups.idea.events.IdeaAcceptedEvent;
import agh.ddd.groups.idea.events.IdeaProposedEvent;
import agh.ddd.groups.idea.events.IdeaRejectedEvent;
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
	    
	    public void accept() {
	    	if (this.state != IdeaState.PROPOSED) {
	    		throw new IllegalStateException("Illegal operation.");
	    	}
	    	
	    	apply(new IdeaAcceptedEvent(this.id));
	    }
	    
	    public void reject() {
	    	if (this.state != IdeaState.ACCEPTED && this.state != IdeaState.PROPOSED) {
	    		throw new IllegalStateException("Illegal operation.");
	    	}
	    	
	    	apply(new IdeaRejectedEvent(this.id));
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
	    
	    @EventSourcingHandler
	    public void onIdeaAccepted(IdeaAcceptedEvent event) {
	    	this.state = IdeaState.ACCEPTED;
	    }
	    
	    @EventSourcingHandler
	    public void onIdeaRejected(IdeaRejectedEvent event) {
	    	this.state = IdeaState.REJECTED;
	    }
}
