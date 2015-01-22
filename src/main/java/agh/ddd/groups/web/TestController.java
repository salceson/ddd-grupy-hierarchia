package agh.ddd.groups.web;

import agh.ddd.groups.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Michał Ciołczyk
 */
@Controller
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping(value = "/")
    public String testApplication(ModelAndView modelAndView){
        return "index";
    }
}
