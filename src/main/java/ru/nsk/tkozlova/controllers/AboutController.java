package ru.nsk.tkozlova.controllers;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @project CitizenApplication
 * @autor Toma on 5/4/2018.
 */

@Controller
@RequestMapping("/about")
@ComponentScan("ru.nsk.tkozlova")
public class AboutController {
    @RequestMapping(method = RequestMethod.GET)
    public String aboutPage(ModelMap model) {
        return "about";
    }
}
