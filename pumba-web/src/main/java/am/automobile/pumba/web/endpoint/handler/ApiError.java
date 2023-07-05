package am.automobile.pumba.web.endpoint.handler;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ApiError {

    private HttpStatus status;
    private String message;
    private Map<String, String> errors;

    public ApiError() {
    }

    public ApiError(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public ApiError(HttpStatus status, String message, HashMap<String, String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }

    public void addError(String field, String message) {
        if (errors == null) {
            errors = new HashMap<>();
        }
        errors.put(field, message);
    }
}
