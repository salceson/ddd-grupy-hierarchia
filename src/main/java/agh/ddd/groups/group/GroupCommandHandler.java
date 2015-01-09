package agh.ddd.groups.group;

import agh.ddd.groups.group.commands.AddMemberToGroupCommand;
import agh.ddd.groups.group.commands.CreateGroupCommand;
import agh.ddd.groups.group.commands.MoveMemberBetweenGroupsCommand;
import agh.ddd.groups.group.commands.RemoveMemberCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by mikolaj on 08.01.15.
 */
public class GroupCommandHandler {
    private Repository<Group> groupRepository;

    @CommandHandler
    public void handleCreateGroupCommand(CreateGroupCommand command) {
        Group group = GroupFactory.create(command.getGroupId(), command.getConfiguration());
        groupRepository.add(group);
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
