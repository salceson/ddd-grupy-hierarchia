package agh.ddd.groups.group;

import agh.ddd.groups.group.commands.AddMemberToGroupCommand;
import agh.ddd.groups.group.commands.CreateGroupCommand;
import agh.ddd.groups.group.commands.RemoveMemberCommand;
import agh.ddd.groups.group.events.GroupCreatedEvent;
import agh.ddd.groups.group.events.MemberAddedEvent;
import agh.ddd.groups.group.events.MemberRemovedEvent;
import agh.ddd.groups.group.valueobjects.Email;
import agh.ddd.groups.group.valueobjects.GroupConfiguration;
import agh.ddd.groups.group.valueobjects.GroupId;
import org.axonframework.test.Fixtures;
import org.axonframework.test.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mikolaj on 09.01.15.
 */
public class GroupTest {
    public static final int MAX_GROUP_SIZE = 3;
    private FixtureConfiguration fixture;
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
        groupId = new GroupId();
        member = new Member(email, "Jan", "Kowalski");
        configuration = new GroupConfiguration(MAX_GROUP_SIZE);
    }

    @Test
    public void createGroupCommandShouldCreateNewGroup() {
        fixture.given()
                .when(
                        new CreateGroupCommand(groupId, configuration)
                )
                .expectEvents(
                        new GroupCreatedEvent(groupId, configuration)
                );
    }

    @Test
    public void addMemberCommandShouldAddMember() {
        fixture
                .given(
                        new GroupCreatedEvent(groupId, configuration)
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
                        new GroupCreatedEvent(groupId, configuration),
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
