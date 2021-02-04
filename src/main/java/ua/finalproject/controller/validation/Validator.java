package ua.finalproject.controller.validation;

import java.util.Map;

public interface Validator<E> {
    void validate(E e);
    E clearInvalidFields(E e);
    boolean hasErrors();
    Map<String, Boolean> getErrors();
}
