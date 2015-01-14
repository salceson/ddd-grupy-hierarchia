	package agh.ddd.groups.idea;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

import agh.ddd.groups.idea.commands.ProposeIdeaCommand;
import agh.ddd.groups.idea.events.IdeaProposedEvent;
import agh.ddd.groups.idea.valueobject.IdeaId;

public class IdeaTest {

    private FixtureConfiguration fixture;
	private IdeaId ideaId;
	private int sectionId;
    private String title;
    private String description;
    private String author;

    @Before
    public void setUp() throws Exception {
        fixture = Fixtures.newGivenWhenThenFixture(Idea.class);

        IdeaCommandHandler commandHandler = new IdeaCommandHandler();
        commandHandler.setIdeaRepository(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(commandHandler);

        ideaId = IdeaId.of("1234");
        sectionId = 123;
        title = "Foobar";
        description = "Lorem ipsum dolor sit amet";
        author = "Bartek Mequrel";
    }

    @Test
    public void proposeIdeaCommandShouldGenerateIdeaProposedEvent() throws Exception {
        fixture.givenNoPriorActivity()
                .when(
                        new ProposeIdeaCommand(ideaId, sectionId, title, description, author)
                )
                .expectEvents(
                        new IdeaProposedEvent(ideaId, sectionId, title, description, author)
                );

    }
}