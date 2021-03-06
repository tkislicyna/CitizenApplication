package ru.nsk.tkozlova.controllers;

import org.hibernate.validator.valuehandling.UnwrapValidatedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.HtmlUtils;
import ru.nsk.tkozlova.controllers.model.CitizenModel;
import ru.nsk.tkozlova.controllers.model.DocumentModel;
import ru.nsk.tkozlova.controllers.model.SearchModel;
import ru.nsk.tkozlova.model.Citizen;
import ru.nsk.tkozlova.model.IdentityDocument;
import ru.nsk.tkozlova.services.CitizenService;
import ru.nsk.tkozlova.validators.CitizenFormValidator;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @project CitizenApplication
 * @autor Toma on 4/26/2018.
 */
@Controller
@ComponentScan("ru.nsk.tkozlova")
public class CitizenController {

    private static final Integer KEYWORD_MIN_LENGTH = 3;

    @Autowired
    CitizenService service;

    @Autowired
    CitizenFormValidator citizenFormValidator;

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listPage(ModelMap model) {
        List<Citizen> citizens = this.service.findAllCitizens();
        model.addAttribute("citizens", citizens);
        return "list";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String findProduct(@RequestParam String search) {
        if (search.isEmpty()) {
            return "redirect:/";
        } else {
            return "redirect:/" + search;
        }
    }


    @RequestMapping(value = "/citizen/{id}/view", method = RequestMethod.GET)
    public String view(@PathVariable("id") int id, Model model) {
        Citizen citizen = service.findById(id);
        model.addAttribute("citizenForm", prepareModel(citizen));
        return "citizen/view";
    }


    @RequestMapping(value = "/citizen/{id}/update", method = RequestMethod.GET)
    public String showUpdateCitizenForm(@PathVariable("id") int id, Model model) {
        Citizen citizen = service.findById(id);
        model.addAttribute("citizenForm", prepareModel(citizen));
        return "citizen/update";
    }

    private CitizenModel prepareModel(Citizen citizen) {
        CitizenModel model = new CitizenModel();
        model.setId(citizen.getId());
        model.setFirstName(citizen.getFirstName());
        model.setMiddleName(citizen.getMiddleName());
        model.setLastName(citizen.getLastName());
        model.setAddress(citizen.getAddress());
        model.setBirthDay(citizen.getBirthDay());

        for (IdentityDocument doc : citizen.getDocuments()) {
            model.getDocuments().add(new DocumentModel(doc.getId(), doc.getType()));
        }

        return model;
    }

    @RequestMapping(value = "/citizen/add", method = RequestMethod.GET)
    public String showAddCitisenForm(Model model) {
        model.addAttribute("citizenForm", new CitizenModel());
        return "citizen/add";
    }

    @RequestMapping(value = "/citizen", method = RequestMethod.POST)
    public String addOrUpdateCitizen(@ModelAttribute("citizenForm") @Validated CitizenModel citizenModel,
                                     BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
        citizenFormValidator.validate(citizenModel, result);

        if (result.hasErrors()) {
            return "citizen/form";
        } else {

            redirectAttributes.addFlashAttribute("css", "success");
            Citizen citizen = null;
            if (citizenModel.isNew()) {
                redirectAttributes.addFlashAttribute("msg", "message.citizen.is.added");
                citizen = new Citizen();
                fullEntityByModel(citizen, citizenModel);
                service.saveCitizen(citizen);
            } else {
                redirectAttributes.addFlashAttribute("msg", "message.citizen.is.updated");
                citizen = service.findById(citizenModel.getId());
                fullEntityByModel(citizen, citizenModel);
                service.updateCitizen(citizen);
            }

            return "redirect:/citizen/" + citizen.getId() + "/view";
        }
    }

    private void fullEntityByModel(Citizen citizen, CitizenModel model) {
        citizen.setFirstName(model.getFirstName());
        citizen.setMiddleName(model.getMiddleName());
        citizen.setLastName(model.getLastName());
        citizen.setAddress(model.getAddress());
        citizen.setBirthDay(model.getBirthDay());
    }

    @RequestMapping(value = "/citizen/{id}/delete", method = RequestMethod.GET)
    public String deleteCitizen(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
        service.deleteCitizenById(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "message.citizen.is.deleted");

        return "redirect:/list";
    }

    @RequestMapping(value = "/citizen/search", method = RequestMethod.POST)
    public String search(@ModelAttribute("searchForm") SearchModel searchModel, Model model, final RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
        String keyword = searchModel.getKeyword();
        if (keyword == null || keyword.length() < KEYWORD_MIN_LENGTH) {
            redirectAttributes.addFlashAttribute("css", "warning");
            redirectAttributes.addFlashAttribute("msg", "message.keyword.valid");
            return "redirect:/list";
        }

        List<Citizen> citizens = service.findCitizensByKeyword(keyword);
        if (citizens.isEmpty()) {
            model.addAttribute("css", "info");
            model.addAttribute("msg", "message.keyword.empty_result");
            model.addAttribute("msgParam", keyword);
        } else {
            model.addAttribute("css", "success").addAttribute(keyword);
            model.addAttribute("msg", "message.keyword.result");
            model.addAttribute("msgParam", keyword);
        }
        model.addAttribute("citizens", citizens);
        return "list";
    }
}
