package ba.nwt.electionmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<ErrorDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErrorDetails> errorResponses = new ArrayList<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            ErrorDetails errorResponse = new ErrorDetails(LocalDateTime.now(),fieldError.getField(),fieldError.getDefaultMessage());
            errorResponses.add(errorResponse);
        }
        return errorResponses;
    }
}