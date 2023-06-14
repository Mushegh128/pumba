package am.automobile.pumba.web.endpoint;

import am.automobile.pumba.core.service.ContactService;
import com.automobile.pumba.data.transfer.response.ContactEmailResponse;
import com.automobile.pumba.data.transfer.response.ContactPhoneResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contact")
@CrossOrigin("*")
public class contactEndpoint {

    private final ContactService contactService;

    @GetMapping("/phone")
    public ResponseEntity<List<ContactPhoneResponse>> findAllContactPhones() {
        return ResponseEntity.ok(contactService.findAllContactPhones());
    }

    @GetMapping("/email")
    public ResponseEntity<List<ContactEmailResponse>> findAllContactEmails() {
        return ResponseEntity.ok(contactService.findAllContactEmails());
    }
}
