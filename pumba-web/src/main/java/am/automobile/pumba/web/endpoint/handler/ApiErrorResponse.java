package am.automobile.pumba.web.endpoint.handler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ApiErrorResponse {

    private HttpStatus status;
    private String message;
    private Map<String, String> errors;


    public ApiErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiErrorResponse(HttpStatus status) {
        this.status = status;
    }

    public void addError(String field, String message) {
        if (errors == null) {
            errors = new HashMap<>();
        }
        errors.put(field, message);
    }
}
