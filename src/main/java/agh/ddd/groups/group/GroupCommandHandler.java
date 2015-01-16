package agh.ddd.groups.group;

import agh.ddd.groups.group.commands.*;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class GroupCommandHandler {
    private Repository<Group> groupRepository;

    @CommandHandler
    public void handleCreateGroupCommand(CreateGroupCommand command) {
        Group group = GroupFactory.create(command.getGroupId(), command.getConfiguration(), command.getName());
        groupRepository.add(group);
    }

    @CommandHandler
    public void handleCloseGroupCommand(CloseGroupCommand command) {
        Group group = groupRepository.load(command.getId());
        group.close();
    }

    @CommandHandler
    public void handleAddMembersCommand(AddMembersCommand command) {
        Group group = groupRepository.load(command.getId());
        group.addMembers(command.getMembers());
    }

    @CommandHandler
    public void handleRemoveMembersCommand(RemoveMembersCommand command) {
        Group group = groupRepository.load(command.getId());
        group.removeMembers(command.getMembers());
    }

    @CommandHandler
    public void handleSplitGroupCommand(SplitGroupCommand command) {
        Group group = groupRepository.load(command.getOldGroupId());
        group.split(command.getNewGroupId(), command.getNewGroupName(), command.getMovedMembers());
    }

    @CommandHandler
    public void handleAddMemberToGroupCommand(AddMemberToGroupCommand command) {
        Group group = groupRepository.load(command.getGroupId());
        group.addMember(command.getMember());
    }

    @CommandHandler
    public void handleRemoveMemberFromGroupCommand(RemoveMemberCommand command) {
        Group group = groupRepository.load(command.getGroupId());
        group.removeMember(command.getMember());
    }

    @CommandHandler
    public void handlerMoveMemberBetweenGroupsCommand(MoveMemberBetweenGroupsCommand command) {
        Group from = groupRepository.load(command.getFromGroup());
        Group to = groupRepository.load(command.getToGroup());
        //todo when second group is full a member will be only removed from the first group and not added to the second one
        from.removeMember(command.getMember());
        to.addMember(command.getMember());
    }

    @Autowired
    @Qualifier("groupRepository")
    public void setGroupRepository(Repository<Group> groupRepository) {
        this.groupRepository = groupRepository;
    }
}
