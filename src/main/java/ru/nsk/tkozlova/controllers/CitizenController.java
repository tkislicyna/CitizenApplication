package ru.nsk.tkozlova.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.nsk.tkozlova.model.Citizen;
import ru.nsk.tkozlova.services.CitizenService;

import java.util.List;

/**
 * @project CitizenApplication
 * @autor Toma on 4/26/2018.
 */
@Controller
@RequestMapping("/")
@ComponentScan("ru.nsk.tkozlova")
public class CitizenController {

    @Autowired
    CitizenService service;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = { "/"}, method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        return "home";
    }

    @RequestMapping(value = { "/list"}, method = RequestMethod.GET)
    public String listPage(ModelMap model) {
        List<Citizen> citizens = this.service.findAllCitizens();
        model.addAttribute("citizens", citizens);
        return "list";
    }

}
