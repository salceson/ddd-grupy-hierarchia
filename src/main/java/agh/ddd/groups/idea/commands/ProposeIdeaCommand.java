package agh.ddd.groups.idea.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import agh.ddd.groups.idea.valueobject.IdeaId;

public class ProposeIdeaCommand {
    @TargetAggregateIdentifier
    private IdeaId id;
    private int sectionId;
    private String title;
    private String description;
    private String author;

    public ProposeIdeaCommand(IdeaId id, int sectionId, String title,
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
