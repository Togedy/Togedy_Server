package Togedy.server.Util.Exception;

import Togedy.server.Util.Exception.Validation.ValidationException;
import org.springframework.validation.BindingResult;

public class FieldValidationException extends ValidationException {

    public FieldValidationException(BindingResult bindingResult) {
        super(bindingResult);
    }
}
