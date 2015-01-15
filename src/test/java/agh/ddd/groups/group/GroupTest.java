package agh.ddd.groups.group;

import agh.ddd.groups.group.commands.CloseGroupCommand;
import agh.ddd.groups.group.commands.CreateGroupCommand;
import agh.ddd.groups.group.commands.SplitGroupCommand;
import agh.ddd.groups.group.events.GroupClosedEvent;
import agh.ddd.groups.group.events.GroupCreatedEvent;
import agh.ddd.groups.group.events.GroupSplitEvent;
import agh.ddd.groups.group.events.MembersAddedEvent;
import agh.ddd.groups.group.exceptions.GroupAlreadyClosedException;
import agh.ddd.groups.group.valueobjects.GroupId;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.axonframework.test.matchers.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.axonframework.test.matchers.Matchers.equalTo;
import static org.axonframework.test.matchers.Matchers.listWithAnyOf;


public class GroupTest {
    private FixtureConfiguration<Group> fixture;

    private static final String GROUP_ID = "1234";
    private static final String GROUP_NAME = "Gotham Citizens";

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

    @Test
    @Ignore
    public void splitGroupCommandShouldCreateNewGroupAndMoveSomeMembers() throws Exception {
        //todo: should I test it this way or just test of saga would be enough?
        String otherGroupName = "Gotham Watchers";
        GroupId oldGroupId = GroupId.of("1234");
        GroupId newGroupId = GroupId.of("9876");

        Member alfred = new Member("alfred.pennyworth@gotham.com");
        Member bruce = new Member("bruce@wayne.com");

        Set<Member> allMembers = new HashSet<>();
        allMembers.add(alfred);
        allMembers.add(bruce);

        Set<Member> movedMemberSet = new HashSet<>();
        movedMemberSet.add(bruce);

        fixture
                .given(
                        new GroupCreatedEvent(oldGroupId, GROUP_NAME),
                        new MembersAddedEvent(oldGroupId, allMembers)
                )
                .when(
                        new SplitGroupCommand(oldGroupId, newGroupId, otherGroupName, movedMemberSet)
                )
                .expectEventsMatching(
                        listWithAnyOf(equalTo(new GroupSplitEvent(oldGroupId, newGroupId))));
    }
}
