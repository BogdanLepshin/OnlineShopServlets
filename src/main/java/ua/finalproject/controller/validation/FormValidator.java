package ua.finalproject.controller.validation;

import ua.finalproject.model.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidator {
    private Map<String, Boolean> errors;

    public User validateRegistrationForm(User user) {
        errors = new HashMap<>();

        if (!validateField(user.getFirstName())) {
            errors.put("firstNameError", true);
            user.setFirstName("");
        }
        if (!validateField(user.getLastName())) {
            errors.put("lastNameError", true);
            user.setLastName("");
        }
        if (!validateField(user.getEmail(), "^[\\w]+@[\\w]+\\.[\\w]+$")) {
            errors.put("emailError", true);
            user.setEmail("");
        }
        if (!validateField(user.getPassword(), 8)) {
            errors.put("passwordError", true);
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

    private boolean validateField(String param) {
        return param != null && !param.isEmpty();
    }

    private boolean validateField(String param, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(param);
        return param != null && !param.isEmpty() && matcher.matches();
    }

    private boolean validateField(String param, int minSize) {
        return param != null && !param.isEmpty() && param.length() >= minSize;
    }

    private boolean validateField(String param, int minSize, int maxSize) {
        return param != null && !param.isEmpty() && param.length() >= minSize && param.length() <= maxSize;
    }
}
