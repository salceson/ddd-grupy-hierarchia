package agh.ddd.groups.idea;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import agh.ddd.groups.idea.commands.AcceptIdeaCommand;
import agh.ddd.groups.idea.commands.ChooseLeaderCommand;
import agh.ddd.groups.idea.commands.ConfirmIdeaCommand;
import agh.ddd.groups.idea.commands.ProposeIdeaCommand;
import agh.ddd.groups.idea.commands.RejectIdeaCommand;

public class IdeaCommandHandler {
	private Repository<Idea> ideaRepository;

	@CommandHandler
	public void handleProposeIdeaCommand(ProposeIdeaCommand command) {
		Idea idea = IdeaFactory.create(command.getId(), command.getSectionId(),
				command.getTitle(), command.getDescription(),
				command.getAuthor());
		ideaRepository.add(idea);
	}

	@CommandHandler
	public void handleAcceptIdeaCommand(AcceptIdeaCommand command) {
		final Idea idea = ideaRepository.load(command.getId());
		idea.accept();
	}

	@CommandHandler
	public void handleRejectIdeaCommand(RejectIdeaCommand command) {
		final Idea idea = ideaRepository.load(command.getId());
		idea.reject();
	}
	
	@CommandHandler
	public void handleConfirmIdeaCommand(ConfirmIdeaCommand command) {
		final Idea idea = ideaRepository.load(command.getId());
		idea.confirm();
	}

	@CommandHandler
	public void handleChooseLeaderCommand(ChooseLeaderCommand command) {
		final Idea idea = ideaRepository.load(command.getIdeaId());
		idea.setLeader(command.getLeaderUserId());
	}
	
	@Autowired
	@Qualifier("ideaRepository")
	public void setIdeaRepository(Repository<Idea> ideaRepository) {
		this.ideaRepository = ideaRepository;
	}
}
