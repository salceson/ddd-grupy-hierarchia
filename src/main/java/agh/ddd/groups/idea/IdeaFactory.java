package agh.ddd.groups.idea;

import agh.ddd.groups.idea.valueobject.IdeaId;

public class IdeaFactory {
    public static Idea create(IdeaId ideaId, int sectionId, String title, String description, String author) {
        return new Idea(ideaId, sectionId, title, description, author);
    }
}
