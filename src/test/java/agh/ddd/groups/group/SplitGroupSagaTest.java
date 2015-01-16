package agh.ddd.groups.group;

import agh.ddd.groups.group.commands.AddMembersCommand;
import agh.ddd.groups.group.commands.CreateGroupCommand;
import agh.ddd.groups.group.commands.RemoveMembersCommand;
import agh.ddd.groups.group.events.*;
import agh.ddd.groups.group.sagas.SplitGroupSaga;
import agh.ddd.groups.group.valueobjects.GroupConfiguration;
import agh.ddd.groups.group.valueobjects.GroupId;
import org.axonframework.test.saga.AnnotatedSagaTestFixture;
import org.joda.time.Duration;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.axonframework.test.matchers.Matchers.equalTo;
import static org.axonframework.test.matchers.Matchers.listWithAnyOf;
import static org.axonframework.test.matchers.Matchers.payloadsMatching;

public class SplitGroupSagaTest {
    private static final int MAX_GROUP_SIZE = 3;
    private AnnotatedSagaTestFixture fixture;
    private GroupId oldGroupId = GroupId.of(UUID.randomUUID());
    private GroupId newGroupId = GroupId.of(UUID.randomUUID());
    private String newGroupName = "Ble";
    private Set<Member> movedMembers = new HashSet<>();
    private GroupConfiguration configuration = new GroupConfiguration(MAX_GROUP_SIZE);

    @Before
    public void setUp() throws Exception {
        fixture = new AnnotatedSagaTestFixture(SplitGroupSaga.class);
    }

    @Test
    public void sagaShouldSendCreateNewGroupCommandAfterSplitStartedEvent() throws Exception {
        fixture
                .whenAggregate(oldGroupId)
                .publishes(new GroupSplitStartedEvent(oldGroupId, newGroupId, newGroupName, movedMembers, configuration))
                .expectDispatchedCommandsMatching(
                        payloadsMatching(listWithAnyOf(equalTo(new CreateGroupCommand(newGroupId, configuration, newGroupName)))));
    }

    @Test
    public void sagaShouldSendRemoveMembersCommandAfterSplitStartedEvent() throws Exception {
        fixture
                .whenAggregate(oldGroupId)
                .publishes(new GroupSplitStartedEvent(oldGroupId, newGroupId, newGroupName, movedMembers, configuration))
                .expectDispatchedCommandsMatching(
                        payloadsMatching(listWithAnyOf(equalTo(new RemoveMembersCommand(oldGroupId, movedMembers))))
                );
    }

    @Test
    public void sagaShouldSendAddMembersCommandToNewGroupAfterNewGroupIsCreated() throws Exception {
        fixture
                .givenAPublished(new GroupSplitStartedEvent(oldGroupId, newGroupId, newGroupName, movedMembers, configuration))
                .whenAggregate(newGroupId)
                .publishes(new GroupCreatedEvent(newGroupId, configuration, newGroupName))
                .expectDispatchedCommandsEqualTo(new AddMembersCommand(newGroupId, movedMembers));
    }

    @Test
    public void sagaShouldWaitWhenMembersAreNotRemovedFromOldGroupAndAddedToNewGroup() throws Exception {
        fixture
                .givenAPublished(new GroupSplitStartedEvent(oldGroupId, newGroupId, newGroupName, movedMembers, configuration))
                .andThenAggregate(newGroupId)
                .published(new GroupCreatedEvent(newGroupId, configuration, newGroupName))
                .whenAggregate(newGroupId)
                .publishes(new MembersAddedEvent(newGroupId, movedMembers))
                .expectActiveSagas(1)
                .expectNoScheduledEvents();
    }

    @Test
    public void sagaShouldWaitWhenMembersAreNRemovedFromOldGroupAndNotAddedToNewGroup() throws Exception {
        fixture
                .givenAPublished(new GroupSplitStartedEvent(oldGroupId, newGroupId, newGroupName, movedMembers, configuration))
                .whenAggregate(oldGroupId)
                .publishes(new MembersRemovedEvent(newGroupId, movedMembers))
                .expectActiveSagas(1)
                .expectNoScheduledEvents();
    }

    @Test
    public void sagaShouldBeEndedWhenGroupIsSuccessfullySplit() throws Exception {
        //old group removes members, then new group is created and members are added
        fixture
                .givenAPublished(new GroupSplitStartedEvent(oldGroupId, newGroupId, newGroupName, movedMembers, configuration))
                .andThenAggregate(oldGroupId)
                .published(new MembersRemovedEvent(oldGroupId, movedMembers))
                .andThenAggregate(newGroupId)
                .published(new CreateGroupCommand(newGroupId, configuration, newGroupName))
                .whenAggregate(newGroupId)
                .publishes(new MembersAddedEvent(newGroupId, movedMembers))
                .expectActiveSagas(0);

        //new group is created and members are added, then old group removes members
        fixture
                .givenAPublished(new GroupSplitStartedEvent(oldGroupId, newGroupId, newGroupName, movedMembers, configuration))
                .andThenAggregate(newGroupId)
                .published(new CreateGroupCommand(newGroupId, configuration, newGroupName))
                .andThenAggregate(newGroupId)
                .published(new MembersAddedEvent(newGroupId, movedMembers))
                .whenAggregate(oldGroupId)
                .publishes(new MembersRemovedEvent(oldGroupId, movedMembers))
                .expectActiveSagas(0);
    }

    @Test
    public void sagaShouldSendEventWhenGroupIsSuccessfullySplit() throws Exception {
        //old group removes members, then new group is created and members are added
        fixture
                .givenAPublished(new GroupSplitStartedEvent(oldGroupId, newGroupId, newGroupName, movedMembers, configuration))
                .andThenAggregate(oldGroupId)
                .published(new MembersRemovedEvent(oldGroupId, movedMembers))
                .andThenAggregate(newGroupId)
                .published(new CreateGroupCommand(newGroupId, configuration, newGroupName))
                .whenAggregate(newGroupId)
                .publishes(new MembersAddedEvent(newGroupId, movedMembers))
                .expectScheduledEvent(Duration.ZERO, new GroupSplitEvent(oldGroupId, newGroupId));

        //new group is created and members are added, then old group removes members
        fixture
                .givenAPublished(new GroupSplitStartedEvent(oldGroupId, newGroupId, newGroupName, movedMembers, configuration))
                .andThenAggregate(newGroupId)
                .published(new CreateGroupCommand(newGroupId, configuration, newGroupName))
                .andThenAggregate(newGroupId)
                .published(new MembersAddedEvent(newGroupId, movedMembers))
                .whenAggregate(oldGroupId)
                .publishes(new MembersRemovedEvent(oldGroupId, movedMembers))
                .expectScheduledEvent(Duration.ZERO, new GroupSplitEvent(oldGroupId, newGroupId));
    }
}
