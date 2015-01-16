package agh.ddd.groups.group;

import agh.ddd.groups.group.commands.*;
import agh.ddd.groups.group.events.*;
import agh.ddd.groups.group.exceptions.GroupAlreadyClosedException;
import agh.ddd.groups.group.valueobjects.Email;
import agh.ddd.groups.group.valueobjects.GroupConfiguration;
import agh.ddd.groups.group.valueobjects.GroupId;
import org.axonframework.test.Fixtures;
import org.axonframework.test.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class GroupTest {
    public static final int MAX_GROUP_SIZE = 3;
    private static final String GROUP_NAME = "Gotham Citizens";

    private FixtureConfiguration<Group> fixture;
    private GroupId groupId;
    private GroupConfiguration configuration;
    private Member member;

    @Before
    public void setUp() {
        fixture = Fixtures.newGivenWhenThenFixture(Group.class);
        GroupCommandHandler commandHandler = new GroupCommandHandler();
        commandHandler.setGroupRepository(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(commandHandler);

        Email email = new Email("example@example.com");
        groupId = new GroupId(UUID.randomUUID());
        member = new Member(email, "Jan", "Kowalski");
        configuration = new GroupConfiguration(MAX_GROUP_SIZE);
    }

    @Test
    public void createGroupCommandShouldCreateNewGroup() {
        fixture.given()
                .when(
                        new CreateGroupCommand(groupId, configuration, GROUP_NAME)
                )
                .expectEvents(
                        new GroupCreatedEvent(groupId, configuration, GROUP_NAME)
                );
    }

    @Test
    public void closeGroupCommandShouldCloseOpenedGroup() throws Exception {
        fixture
                .given(
                        new GroupCreatedEvent(groupId, configuration, GROUP_NAME)
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
                        new GroupCreatedEvent(groupId, configuration, GROUP_NAME),
                        new GroupClosedEvent(groupId)
                )
                .when(
                        new CloseGroupCommand(groupId)
                )
                .expectException(
                        GroupAlreadyClosedException.class
                );
    }

    @Test
    public void addMemberCommandShouldAddMember() {
        fixture
                .given(
                        new GroupCreatedEvent(groupId, configuration, GROUP_NAME)
                )
                .when(
                        new AddMemberToGroupCommand(groupId, member)
                )
                .expectEvents(
                        new MemberAddedEvent(groupId, member)
                );
    }

    @Test
    public void removeMemberCommandShouldRemoveMember() {
        fixture
                .given(
                        new GroupCreatedEvent(groupId, configuration, GROUP_NAME),
                        new MemberAddedEvent(groupId, member)
                )
                .when(
                        new RemoveMemberCommand(groupId, member)
                )
                .expectEvents (
                        new MemberRemovedEvent(groupId, member)
                );
    }

    

}
