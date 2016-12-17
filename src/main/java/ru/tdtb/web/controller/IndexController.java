package ru.tdtb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView root() {
        return new ModelAndView("index");
    }

    public ModelAndView login() {
        return new ModelAndView("common/login");
    }

    public ModelAndView error() {
        return new ModelAndView("common/403");
    }
}
