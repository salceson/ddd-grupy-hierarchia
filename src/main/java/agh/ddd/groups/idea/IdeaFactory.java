package agh.ddd.groups.idea;

import agh.ddd.groups.idea.commands.ProposeIdeaCommand;
import agh.ddd.groups.idea.valueobject.IdeaId;

public class IdeaFactory {
    public static Idea create(ProposeIdeaCommand command) {
        return new Idea(command.getId(), command.getSectionId(),
                command.getTitle(), command.getDescription(),
                command.getAuthor());
    }
}
