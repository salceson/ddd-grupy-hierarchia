package agh.ddd.groups.idea;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

import agh.ddd.groups.idea.commands.AcceptIdeaCommand;
import agh.ddd.groups.idea.commands.ChooseLeaderCommand;
import agh.ddd.groups.idea.commands.ConfirmIdeaCommand;
import agh.ddd.groups.idea.commands.ProposeIdeaCommand;
import agh.ddd.groups.idea.commands.RejectIdeaCommand;
import agh.ddd.groups.idea.events.IdeaAcceptedEvent;
import agh.ddd.groups.idea.events.IdeaConfirmedEvent;
import agh.ddd.groups.idea.events.IdeaProposedEvent;
import agh.ddd.groups.idea.events.IdeaPublishedEvent;
import agh.ddd.groups.idea.events.IdeaRejectedEvent;
import agh.ddd.groups.idea.events.LeaderChosenEvent;
import agh.ddd.groups.idea.valueobject.IdeaId;

public class IdeaTest {

    private FixtureConfiguration fixture;
    private IdeaId ideaId;
    private int sectionId;
    private int leaderUserId;
    private int anotherLeaderUserId;
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
        leaderUserId = 321;
        anotherLeaderUserId = 432;
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


    @Test
    public void acceptIdeaCommandShouldGenerateIdeaAcceptedEventAndIdeaPublishedEvent() throws Exception {
        fixture.given(
                new IdeaProposedEvent(ideaId, sectionId, title, description, author)
        )
                .when(
                        new AcceptIdeaCommand(ideaId)
                )
                .expectEvents(
                        new IdeaAcceptedEvent(ideaId),
                        new IdeaPublishedEvent(ideaId)
                );
    }

    @Test
    public void acceptIdeaCommandShouldThrowExceptionIfIdeaWasAlreadyAccepted() throws Exception {
        fixture
                .given(
                        new IdeaProposedEvent(ideaId, sectionId, title, description, author),
                        new IdeaAcceptedEvent(ideaId)
                )
                .when(
                        new AcceptIdeaCommand(ideaId)
                )
                .expectException(
                        IllegalStateException.class
                );
    }


    @Test
    public void acceptIdeaCommandShouldThrowExceptionIfIdeaWasAlreadyRejected() throws Exception {
        fixture
                .given(
                        new IdeaProposedEvent(ideaId, sectionId, title, description, author),
                        new IdeaRejectedEvent(ideaId)
                )
                .when(
                        new AcceptIdeaCommand(ideaId)
                )
                .expectException(
                        IllegalStateException.class
                );
    }

    @Test
    public void rejectIdeaCommandShouldGenerateIdeaRejectedEvent() throws Exception {
        fixture.given(
                new IdeaProposedEvent(ideaId, sectionId, title, description, author)
        )
                .when(
                        new RejectIdeaCommand(ideaId)
                )
                .expectEvents(
                        new IdeaRejectedEvent(ideaId)
                );
    }

    @Test
    public void rejectIdeaCommandShouldThrowExceptionIfIdeaWasAlreadyRejected() throws Exception {
        fixture
                .given(
                        new IdeaProposedEvent(ideaId, sectionId, title, description, author),
                        new IdeaRejectedEvent(ideaId)
                )
                .when(
                        new RejectIdeaCommand(ideaId)
                )
                .expectException(
                        IllegalStateException.class
                );
    }


    @Test
    public void rejectIdeaCommandShouldGenerateIdeaRejectedEventIfIdeaWasAlreadyAccepted() throws Exception {
        fixture
                .given(
                        new IdeaProposedEvent(ideaId, sectionId, title, description, author),
                        new IdeaAcceptedEvent(ideaId)
                )
                .when(
                        new RejectIdeaCommand(ideaId)
                )
                .expectEvents(
                        new IdeaRejectedEvent(ideaId)
                );
    }

    @Test
    public void confirmIdeaCommandShouldGenerateIdeaConfirmedEvent() throws Exception {
        fixture
                .given(
                        new IdeaProposedEvent(ideaId, sectionId, title, description, author),
                        new IdeaAcceptedEvent(ideaId),
                        new LeaderChosenEvent(ideaId, leaderUserId)
                )
                .when(
                        new ConfirmIdeaCommand(ideaId)
                )
                .expectEvents(
                        new IdeaConfirmedEvent(ideaId)
                );
    }

    @Test
    public void confirmIdeaCommandShouldThrowExceptionIfLeaderNotChosen() throws Exception {
        fixture
                .given(
                        new IdeaProposedEvent(ideaId, sectionId, title, description, author),
                        new IdeaAcceptedEvent(ideaId)
                )
                .when(
                        new ConfirmIdeaCommand(ideaId)
                )
                .expectException(
                        IllegalStateException.class
                );
    }

    @Test
    public void chooseLeaderCommandShouldGenerateLeaderChosenEvent() throws Exception {
        fixture
                .given(
                        new IdeaProposedEvent(ideaId, sectionId, title, description, author),
                        new IdeaAcceptedEvent(ideaId)
                )
                .when(
                        new ChooseLeaderCommand(ideaId, leaderUserId)
                )
                .expectEvents(
                        new LeaderChosenEvent(ideaId, leaderUserId)
                );
    }

    @Test
    public void chooseLeaderCommandShouldThrowExceptionIfIdeaRejected() throws Exception {
        fixture
                .given(
                        new IdeaProposedEvent(ideaId, sectionId, title, description, author),
                        new IdeaRejectedEvent(ideaId)
                )
                .when(
                        new ChooseLeaderCommand(ideaId, leaderUserId)
                )
                .expectException(
                        IllegalStateException.class
                );
    }

    @Test
    public void chooseLeaderCommandShouldGenerateLeaderChosenEventIfLeaderAlreadyChosen() throws Exception {
        fixture
                .given(
                        new IdeaProposedEvent(ideaId, sectionId, title, description, author),
                        new IdeaAcceptedEvent(ideaId),
                        new LeaderChosenEvent(ideaId, leaderUserId)
                )
                .when(
                        new ChooseLeaderCommand(ideaId, anotherLeaderUserId)
                )
                .expectEvents(
                        new LeaderChosenEvent(ideaId, anotherLeaderUserId)
                );
    }

    @Test
    public void chooseLeaderCommandShouldThrowExceptionIfSameLeaderChosen() throws Exception {
        fixture
                .given(
                        new IdeaProposedEvent(ideaId, sectionId, title, description, author),
                        new IdeaAcceptedEvent(ideaId),
                        new LeaderChosenEvent(ideaId, leaderUserId)
                )
                .when(
                        new ChooseLeaderCommand(ideaId, leaderUserId)
                )
                .expectException(
                        IllegalStateException.class
                );
    }

    @Test
    public void chooseLeaderCommandShouldGenerateLeaderChosenEventIfIdeaAlreadyConfirmed() throws Exception {
        fixture
                .given(
                        new IdeaProposedEvent(ideaId, sectionId, title, description, author),
                        new IdeaAcceptedEvent(ideaId),
                        new IdeaConfirmedEvent(ideaId)
                )
                .when(
                        new ChooseLeaderCommand(ideaId, leaderUserId)
                )
                .expectEvents(
                        new LeaderChosenEvent(ideaId, leaderUserId)
                );
    }
}