package agh.ddd.groups.group;

import agh.ddd.groups.group.commands.CloseGroupCommand;
import agh.ddd.groups.group.commands.CreateGroupCommand;
import agh.ddd.groups.group.events.GroupClosedEvent;
import agh.ddd.groups.group.events.GroupCreatedEvent;
import agh.ddd.groups.group.exceptions.GroupAlreadyClosedException;
import agh.ddd.groups.group.valueobjects.GroupId;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class GroupTest {
    private FixtureConfiguration<Group> fixture;

    private static final String GROUP_ID = "1234";
    private static final String GROUP_NAME = "Avengers";

    private GroupId groupId;

    @Before
    public void setUp() throws Exception {
        fixture = Fixtures.newGivenWhenThenFixture(Group.class);

        GroupCommandHandler commandHandler = new GroupCommandHandler();
        commandHandler.setGroupRepository(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(commandHandler);

        groupId = GroupId.of(GROUP_ID);
    }

    @Test
    public void createGroupCommandShouldCreateNewGroup() throws Exception {
        fixture.given()
                .when(
                        new CreateGroupCommand(GroupId.of(GROUP_ID), GROUP_NAME)
                )
                .expectEvents(
                        new GroupCreatedEvent(GroupId.of(GROUP_ID), GROUP_NAME)
                );
    }

    @Test
    public void closeGroupCommandShouldCloseOpenedGroup() throws Exception {
        fixture
                .given(
                        new GroupCreatedEvent(groupId, GROUP_NAME)
                )
                .when(
                        new CloseGroupCommand(groupId)
                )
                .expectEvents(
                        new GroupClosedEvent(groupId)
                );
    }

    @Test
    public void closeGroupCommandShouldThrowExceptionWhenGroupIsAlreadyClosed() throws Exception {
        fixture
                .given(
                        new GroupCreatedEvent(groupId, GROUP_NAME),
                        new GroupClosedEvent(groupId)
                )
                .when(
                        new CloseGroupCommand(groupId)
                )
                .expectException(
                        GroupAlreadyClosedException.class
                );
    }
}
