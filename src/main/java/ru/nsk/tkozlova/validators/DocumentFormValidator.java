package ru.nsk.tkozlova.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.nsk.tkozlova.controllers.model.DocumentModel;

/**
 * @project documentApplication
 * @autor Toma on 5/6/2018.
 */
@Component
public class DocumentFormValidator implements Validator {
    private static final Integer MAX_LENGTH_TEXT = 100;


    @Override
    public boolean supports(Class<?> aClass) {
        return DocumentModel.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DocumentModel documentModel = (DocumentModel) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "not_empty.document_form.type");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "authority", "not_empty.document_form.authority");

        if (documentModel.getIssueDate() == null) {
            errors.rejectValue("issueDate", "not_empty.document_form.issue_date");
        }

        if (documentModel.getExpiryDate() == null) {
            errors.rejectValue("expiryDate", "not_empty.document_form.expiry_date");
        }


        if (documentModel.getAuthority() == null) {
            errors.rejectValue("authority", "not_empty.document_form.authority");
        } else if (documentModel.getAuthority().length() > MAX_LENGTH_TEXT) {
            errors.rejectValue("authority", "valid.document_form.authority");
        }

        if(documentModel.getIssueDate()  != null && documentModel.getExpiryDate() != null &&
        !documentModel.getIssueDate().before(documentModel.getExpiryDate())) {
            errors.rejectValue("expiryDate", "valid.document_form.date_interval");
        }
    }
}
