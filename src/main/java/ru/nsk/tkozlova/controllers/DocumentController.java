package ru.nsk.tkozlova.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.nsk.tkozlova.controllers.model.CitizenModel;
import ru.nsk.tkozlova.controllers.model.DocumentModel;
import ru.nsk.tkozlova.model.Citizen;
import ru.nsk.tkozlova.model.DocumentType;
import ru.nsk.tkozlova.model.IdentityDocument;
import ru.nsk.tkozlova.services.CitizenService;
import ru.nsk.tkozlova.services.DocumentService;
import ru.nsk.tkozlova.validators.DocumentFormValidator;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @project CitizenApplication
 * @autor Toma on 5/5/2018.
 */
@Controller
@ComponentScan("ru.nsk.tkozlova")
public class DocumentController {

    @Autowired
    DocumentService service;

    @Autowired
    CitizenService citizenService;

    @Autowired
    DocumentFormValidator documentFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(documentFormValidator);
    }


    @RequestMapping(value = "/document/{id}/view", method = RequestMethod.GET)
    public String view(@PathVariable("id") int id, Model model) {
        IdentityDocument document = service.findById(id);
        model.addAttribute("documentForm", prepareModel(document));
        return "document/view";
    }

    private DocumentModel prepareModel(IdentityDocument identityDocument) {
        DocumentModel model = new DocumentModel(identityDocument.getId(), identityDocument.getType());
        Citizen citizen = identityDocument.getHolder();

        model.setHolder(new CitizenModel(citizen.getId(), citizen.getFullName()));
        model.setAuthority(identityDocument.getAuthority());
        model.setIssueDate(identityDocument.getIssueDate());
        model.setExpiryDate(identityDocument.getExpiryDate());

        return model;
    }


    // show update form
    @RequestMapping(value = "/document/{id}/update", method = RequestMethod.GET)
    public String showUpdateDocumentForm(@PathVariable("id") int id, Model model) {
        IdentityDocument document = service.findById(id);
        model.addAttribute("documentForm", prepareModel(document));
        model.addAttribute("typeList", getTypeList());

        return "document/update";
    }

    private Map<String, String> getTypeList(){
        Map<String, String> types = new LinkedHashMap<String, String>();
        types.put(DocumentType.PASSPORT.name(), DocumentType.PASSPORT.name());
        types.put(DocumentType.BIRTH_CERTIFICATE.name(), DocumentType.BIRTH_CERTIFICATE.name());
        return types;
    }

    // show add form
    @RequestMapping(value = "/document/{hilderId}/add", method = RequestMethod.GET)
    public String showAddCitisenForm(@PathVariable("hilderId") int holderId, Model model) {
        Citizen citizen = citizenService.findById(holderId);
        model.addAttribute("documentForm", new DocumentModel(new CitizenModel(citizen.getId(), citizen.getFullName())));
        return "document/add";
    }

    // save or update document
    @RequestMapping(value = "/document", method = RequestMethod.POST)
    public String addOrUpdateDocument(@ModelAttribute("documentForm") @Validated DocumentModel documentModel,
                                     BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "document/form";
        } else {

            redirectAttributes.addFlashAttribute("css", "success");
            IdentityDocument document = null;
            if(documentModel.isNew()){
                redirectAttributes.addFlashAttribute("msg", "message.document.is.added");
                document = new IdentityDocument();
                fullEntityByModel(document, documentModel);
                service.saveDocument(document);
            }else{
                redirectAttributes.addFlashAttribute("msg", "message.document.is.updated");
                document = service.findById(documentModel.getId());
                fullEntityByModel(document, documentModel);
                service.updateDocument(document);
            }

            // POST/REDIRECT/GET
            return "redirect:/document/" + document.getId() + "/view";
        }
    }

    private void fullEntityByModel(IdentityDocument document, DocumentModel model){
        if(model.isNew()) {
            document.setHolder(citizenService.findById(model.getHolder().getId()));
        }
        document.setType(model.getType());
        document.setIssueDate(model.getIssueDate());
        document.setExpiryDate(model.getExpiryDate());
        document.setAuthority(model.getAuthority());
    }


    // delete document
    @RequestMapping(value = "/document/{id}/delete", method = RequestMethod.GET)
    public String deleteDocument(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
        IdentityDocument document = service.findById(id);
        Citizen citizen = document.getHolder();
        service.deleteDocumentById(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "message.document.is.deleted");

        return "redirect:/citizen/" + citizen.getId() + "/view";
    }


}
