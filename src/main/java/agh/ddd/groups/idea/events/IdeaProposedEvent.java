package agh.ddd.groups.idea.events;

import agh.ddd.groups.idea.valueobject.IdeaId;

public class IdeaProposedEvent {
    private final IdeaId id;
    private final int sectionId;
    private final String title;
    private final String description;
    private final String author;

    public IdeaProposedEvent(IdeaId id, int sectionId, String title,
                             String description, String author) {
        this.id = id;
        this.sectionId = sectionId;
        this.title = title;
        this.description = description;
        this.author = author;
    }

    public IdeaId getId() {
        return id;
    }

    public int getSectionId() {
        return sectionId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

}
