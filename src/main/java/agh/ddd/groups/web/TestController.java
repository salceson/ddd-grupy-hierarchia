package agh.ddd.groups.web;

import agh.ddd.groups.service.TestService;
import agh.ddd.groups.web.requestobjects.TestForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Michał Ciołczyk
 */
@Controller
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String testApplication(Model model){
        model.addAttribute("test", "Test");
        return "index";
    }

    @RequestMapping(value = "/test/form", method = RequestMethod.GET)
    public String testForm(Model model){
        if(!model.containsAttribute("testForm")){
            model.addAttribute("testForm", new TestForm());
        }
        return "test.form";
    }

    @RequestMapping(value = "/test/form", method = RequestMethod.POST)
    public String handleTestForm(@ModelAttribute TestForm testForm, Model model, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/test/form";
        }
        String name = testForm.getName();
        if(name == null){
            name = "";
        }
        model.addAttribute("name", name);
        return "test.handleform";
    }
}
