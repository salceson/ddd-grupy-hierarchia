package agh.ddd.groups.web;

import agh.ddd.groups.poll.valueobjects.PollId;
import agh.ddd.groups.service.PollService;
import agh.ddd.groups.web.requestobjects.PollForm;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * @author Michal Janczykowski
 */
@Controller
public class PollController {
    @Autowired
    private PollService pollService;

    @RequestMapping(value = "/poll/{id}", method = RequestMethod.GET)
    public String testForm(Model model, @PathVariable("id") String id){
        final PollId pollId = PollId.of(UUID.fromString(id));
        final Optional<PollForm> pollFormOptional = pollService.getPoll(pollId);

        if(!pollFormOptional.isPresent()) {
            throw new IllegalArgumentException("No poll with given id");
        }

        model.addAttribute("poll", pollFormOptional.get());
        return "poll.poll";
    }

    @RequestMapping(value = "/poll/add", method = RequestMethod.GET)
    public String testForm(Model model){
        model.addAttribute("pollForm", new PollForm());
        return "poll.form";
    }

    @RequestMapping(value = "/poll/add", method = RequestMethod.POST)
    public String handleTestForm(@ModelAttribute PollForm poll, Model model, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/poll/new";
        }

        final UUID uuid = UUID.randomUUID();
        final PollId pollId = PollId.of(uuid);

        pollService.addPoll(pollId, poll.getContent(), poll.getDeadline());

        return "redirect:/poll/" + uuid.toString();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public @ResponseBody String handleIllegalArgumentException(IllegalArgumentException e) {
        return e.getMessage();
    }
}
