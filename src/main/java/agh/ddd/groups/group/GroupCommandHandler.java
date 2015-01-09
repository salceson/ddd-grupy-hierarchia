package agh.ddd.groups.group;

import agh.ddd.groups.group.commands.CloseGroupCommand;
import agh.ddd.groups.group.commands.CreateGroupCommand;
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
}
