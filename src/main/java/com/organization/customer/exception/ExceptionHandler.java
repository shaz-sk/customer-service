package com.organization.customer.exception;

import com.organization.customer.models.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody List<Error> handleValidationError(final Exception ex) {
        log.error("Error:{}", ex.getMessage());
        final List<Error> errors = new ArrayList<>();

        if(ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
            for(FieldError error : exception.getBindingResult().getFieldErrors()) {
                errors.add(getExceptionResponse(error.getField(), HttpStatus.BAD_REQUEST.value()));
            }
        }
        errors.add(getExceptionResponse("Invalid field format", HttpStatus.BAD_REQUEST.value()));
        return errors;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = InvalidRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody List<Error> handleValidationError(final InvalidRequestException ex) {
        final List<Error> errors = new ArrayList<>();
        errors.add(getExceptionResponse(ex.getErrors().getAllErrors().get(0).getDefaultMessage(), ex.getErrors().getAllErrors().get(0).getCode()));
        return errors;
    }

    private Error getExceptionResponse(final String message, final Integer errorCode) {
        final Error exceptionResponse = new Error();
        exceptionResponse.setCode(errorCode.toString());
        exceptionResponse.setMessage(message);
        return exceptionResponse;
    }

    private Error getExceptionResponse(final String message, final String errorCode) {
        final Error exceptionResponse = new Error();
        exceptionResponse.setCode(errorCode.toString());
        exceptionResponse.setMessage(message);
        return exceptionResponse;
    }
}
