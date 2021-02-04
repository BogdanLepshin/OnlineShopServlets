package ua.finalproject.controller.validation;

import ua.finalproject.model.entity.User;

import java.util.HashMap;
import java.util.Map;

public class RegistrationFormValidator extends AbstractValidator<User>{
    private Map<String, Boolean> errors;

    @Override
    public void validate(User user) {
        errors = new HashMap<>();

        if (!validateField(user.getFirstName())) {
            errors.put("firstNameError", true);
        }
        if (!validateField(user.getLastName())) {
            errors.put("lastNameError", true);
        }
        if (!validateField(user.getEmail(), "^[\\w]+@[\\w]+\\.[\\w]+$")) {
            errors.put("emailError", true);
        }
        if (!validateField(user.getPassword(), 8)) {
            errors.put("passwordError", true);
        }
    }

    public User clearInvalidFields(User user) {
        if (!hasErrors()) {
            return user;
        }

        if (errors.containsKey("firstNameError")) {
            user.setFirstName("");
        }
        if (errors.containsKey("lastNameError")) {
            user.setLastName("");
        }
        if (errors.containsKey("emailError")) {
            user.setEmail("");
        }
        if (errors.containsKey("passwordError")) {
            user.setPassword("");
        }
        return user;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public Map<String, Boolean> getErrors() {
        return errors;
    }

}
