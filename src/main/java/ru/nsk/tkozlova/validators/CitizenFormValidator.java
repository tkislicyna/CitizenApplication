package ru.nsk.tkozlova.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.nsk.tkozlova.controllers.model.CitizenModel;

/**
 * @project CitizenApplication
 * @autor Toma on 5/4/2018.
 */
@Component
public class CitizenFormValidator implements Validator {
    private static final Integer MAX_LENGTH_NAME = 50;
    private static final Integer MAX_LENGTH_ADDRESS = 100;

    @Override
    public boolean supports(Class<?> clazz) {
        return CitizenModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        CitizenModel citizen = (CitizenModel) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "not_empty.citizen_form.first_name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "middleName", "not_empty.citizen_form.middle_name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "not_empty.citizen_form.last_name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "not_empty.citizen_form.address");

        if (citizen.getFirstName() == null) {
            errors.rejectValue("firstName", "not_empty.citizen_form.first_name");
        } else if (citizen.getFirstName().length() > MAX_LENGTH_NAME) {
            errors.rejectValue("firstName", "valid.citizen_form.first_name");
        }
        
        if (citizen.getMiddleName() == null) {
            errors.rejectValue("middleName", "not_empty.citizen_form.middle_name");
        } else if (citizen.getMiddleName().length() > MAX_LENGTH_NAME) {
            errors.rejectValue("middleName", "valid.citizen_form.middle_name");
        }

        if (citizen.getLastName() == null) {
            errors.rejectValue("lastName", "not_empty.citizen_form.last_name");
        } else if (citizen.getLastName().length() > MAX_LENGTH_NAME) {
            errors.rejectValue("lastName", "valid.citizen_form.last_name");
        }

        if (citizen.getBirthDay() == null) {
            errors.rejectValue("birthDay", "not_empty.citizen_form.birth_day");
        }

        if (citizen.getAddress() == null) {
            errors.rejectValue("address", "not_empty.citizen_form.address");
        } else if (citizen.getAddress().length() > MAX_LENGTH_ADDRESS) {
            errors.rejectValue("address", "valid.citizen_form.address");
        }
    }
}
