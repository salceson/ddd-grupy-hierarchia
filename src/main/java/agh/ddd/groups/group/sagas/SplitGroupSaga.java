package agh.ddd.groups.group.sagas;

import agh.ddd.groups.group.Member;
import agh.ddd.groups.group.commands.AddMembersCommand;
import agh.ddd.groups.group.commands.CreateGroupCommand;
import agh.ddd.groups.group.commands.RemoveMembersCommand;
import agh.ddd.groups.group.events.*;
import agh.ddd.groups.group.valueobjects.GroupId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SplitGroupSaga extends AbstractAnnotatedSaga {
    private transient CommandGateway commandGateway;
    private transient EventScheduler eventScheduler;

    private GroupId oldGroupId;
    private GroupId newGroupId;
    private Set<Member> movedMembers;

    private boolean membersRemovedFromOldGroup = false;
    private boolean membersAddedToNewGroup = false;

    @StartSaga
    @SagaEventHandler(associationProperty = "groupId")
    public void handle(GroupSplitStartedEvent event) {
        oldGroupId = event.getGroupId();
        newGroupId = event.getNewGroupId();
        movedMembers = event.getMovedMembers();
        String newGroupName = event.getNewGroupName();

        associateWith("oldGroupId", oldGroupId.toString());
        associateWith("newGroupId", newGroupId.toString());

        commandGateway.send(new CreateGroupCommand(newGroupId, newGroupName));
        commandGateway.send(new RemoveMembersCommand(oldGroupId, movedMembers));
    }

    @SagaEventHandler(associationProperty = "id", keyName = "newGroupId")
    public void handle(GroupCreatedEvent event) {
        commandGateway.send(new AddMembersCommand(newGroupId, movedMembers));
    }

    @SagaEventHandler(associationProperty = "id", keyName = "newGroupId")
    public void handle(MembersAddedEvent event) {
        membersAddedToNewGroup = true;
        if (membersRemovedFromOldGroup) {
            eventScheduler.schedule(DateTime.now(), new GroupSplitEvent(oldGroupId, newGroupId));
            end();
        }
    }

    @SagaEventHandler(associationProperty = "id", keyName = "groupId")
    public void handle(MembersRemovedEvent event) {
        membersRemovedFromOldGroup = true;
        if (membersAddedToNewGroup) {
            eventScheduler.schedule(DateTime.now(), new GroupSplitEvent(oldGroupId, newGroupId));
            end();
        }
    }


    @Autowired
    public void setCommandGateway(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Autowired
    public void setEventScheduler(EventScheduler eventScheduler) {
        this.eventScheduler = eventScheduler;
    }
}
