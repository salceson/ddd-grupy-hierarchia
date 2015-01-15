package agh.ddd.groups.group;

import agh.ddd.groups.group.commands.*;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GroupCommandHandler {
    private Repository<Group> repository;

    @Autowired
    @Qualifier("groupRepository")
    public void setGroupRepository(Repository<Group> groupRepository) {
        this.repository = groupRepository;
    }

    @CommandHandler
    public void handleCreateGroupCommand(CreateGroupCommand command) {
        Group group = GroupFactory.create(command.getId(), command.getName());
        repository.add(group);
    }

    @CommandHandler
    public void handleCloseGroupCommand(CloseGroupCommand command) {
        Group group = repository.load(command.getId());
        group.close();
    }

    @CommandHandler
    public void handleAddMembersCommand(AddMembersCommand command) {
        Group group = repository.load(command.getId());
        group.addMembers(command.getMembers());
    }

    @CommandHandler
    public void handleRemoveMembersCommand(RemoveMembersCommand command) {
        Group group = repository.load(command.getId());
        group.removeMembers(command.getMembers());
    }

    @CommandHandler
    public void handleSplitGroupCommand(SplitGroupCommand command) {
        Group group = repository.load(command.getOldGroupId());
        group.split(command.getNewGroupId(), command.getNewGroupName(), command.getMovedMembers());
    }
}
