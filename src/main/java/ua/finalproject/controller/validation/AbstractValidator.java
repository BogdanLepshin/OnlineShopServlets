package ua.finalproject.controller.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractValidator<E> implements Validator<E>{

    protected boolean validateField(String param) {
        return param != null && !param.isEmpty();
    }

    protected boolean validateField(String param, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(param);
        return param != null && !param.isEmpty() && matcher.matches();
    }

    protected boolean validateField(String param, int minSize) {
        return param != null && !param.isEmpty() && param.length() >= minSize;
    }

    protected boolean validateField(String param, int minSize, int maxSize) {
        return param != null && !param.isEmpty() && param.length() >= minSize && param.length() <= maxSize;
    }
}
