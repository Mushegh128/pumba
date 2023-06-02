package am.automobile.pumba.web.endpoint.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST, "Validation failed");

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorResponse.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


//    @ExceptionHandler(UserEmailConflictException.class)
//    public ResponseEntity<String> handleCustomException(YourCustomException ex) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body("Bad request: " + ex.getMessage());
//    }
}
