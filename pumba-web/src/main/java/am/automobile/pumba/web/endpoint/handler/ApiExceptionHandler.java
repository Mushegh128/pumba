package am.automobile.pumba.web.endpoint.handler;

import am.automobile.pumba.core.exception.AuthenticatedException;
import am.automobile.pumba.core.exception.EntityNotFoundException;
import am.automobile.pumba.core.exception.FileNotExistException;
import am.automobile.pumba.core.exception.UserEmailConflictException;
import am.automobile.pumba.core.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Validation failed");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            apiError.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            apiError.addError(error.getObjectName(), error.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(AuthenticatedException.class)
    protected ResponseEntity<Object> handleAuthenticatedException(AuthenticatedException ex) {
        log.error(ex.getMessage());
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

    @ExceptionHandler({
            EntityNotFoundException.class,
            FileNotExistException.class,
            UserNotFoundException.class}
    )
    protected ResponseEntity<Object> handleEntityNotFoundException(Exception ex) {
        log.error(ex.getMessage());
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(UserEmailConflictException.class)
    protected ResponseEntity<Object> handleUserEmailConflictException(UserEmailConflictException ex) {
        log.error(ex.getMessage());
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }
}
