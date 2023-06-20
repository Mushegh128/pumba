package am.automobile.pumba.web.endpoint;

import am.automobile.pumba.core.entity.IpAddress;
import am.automobile.pumba.core.facade.UserIdentifyInformationFacade;
import am.automobile.pumba.core.service.ContactSellerService;
import am.automobile.pumba.core.service.ContactService;
import com.automobile.pumba.data.transfer.request.ContactEmailRequest;
import com.automobile.pumba.data.transfer.request.ContactPhoneRequest;
import com.automobile.pumba.data.transfer.request.ContactSellerRequest;
import com.automobile.pumba.data.transfer.response.ContactEmailResponse;
import com.automobile.pumba.data.transfer.response.ContactPhoneResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contact")
@CrossOrigin("*")
public class ContactEndpoint {

    private final ContactService contactService;
    private final ContactSellerService contactSellerService;
    private final UserIdentifyInformationFacade userIdentifyInformationFacade;

    @GetMapping("/phone")
    public ResponseEntity<List<ContactPhoneResponse>> findAllContactPhones() {
        return ResponseEntity.ok(contactService.findAllContactPhones());
    }

    @PostMapping("/phone")
    public ResponseEntity<?> createContactPhone(@Valid @RequestBody ContactPhoneRequest contactPhoneRequest) {
        return ResponseEntity.ok(contactService.createPhone(contactPhoneRequest));
    }

    @PutMapping("/phone/{id}")
    public ResponseEntity<?> updateContactPhone(@Valid @RequestBody ContactPhoneRequest contactPhoneRequest, @PathVariable long id) {
        return ResponseEntity.ok(contactService.updatePhone(id, contactPhoneRequest));
    }

    @DeleteMapping("/phone/{id}")
    public ResponseEntity<?> deleteContactPhone(@PathVariable long id) {
        contactService.deletePhoneById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/email")
    public ResponseEntity<List<ContactEmailResponse>> findAllContactEmails() {
        return ResponseEntity.ok(contactService.findAllContactEmails());
    }

    @PostMapping("/email")
    public ResponseEntity<?> createContactEmails(@Valid @RequestBody ContactEmailRequest contactEmailRequest) {
        return ResponseEntity.ok(contactService.createEmail(contactEmailRequest));
    }

    @PutMapping("/email/{id}")
    public ResponseEntity<?> updateContactEmails(@Valid @RequestBody ContactEmailRequest contactEmailRequest, @PathVariable long id) {
        return ResponseEntity.ok(contactService.updateEmail(id, contactEmailRequest));
    }

    @DeleteMapping("/email/{id}")
    public ResponseEntity<?> deleteContactEmails(@PathVariable long id) {
        contactService.deleteEmailById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/seller")
    public ResponseEntity<?> createContactSeller(
            @RequestBody ContactSellerRequest contactSellerRequest,
            HttpServletRequest request) {
        IpAddress ipAddress = userIdentifyInformationFacade.save(request);
        contactSellerService.save(contactSellerRequest, ipAddress);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/seller/{contactSellerId}")
    public ResponseEntity<?> deleteContactSeller(@PathVariable long contactSellerId) {
        contactSellerService.deleteById(contactSellerId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/seller")
    public ResponseEntity<?> findAll(@PageableDefault(sort = {"createAt"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(contactSellerService.findAll(pageable));
    }
}
