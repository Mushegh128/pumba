package am.automobile.pumba.web.endpoint;

import am.automobile.pumba.core.service.CooperatingOrganizationService;
import com.automobile.pumba.data.transfer.request.CooperatingOrganizationRequest;
import com.automobile.pumba.data.transfer.response.CooperatingOrganizationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/cooperating-organization")
public class CooperatingOrganizationEndpoint {

    private final CooperatingOrganizationService cooperatingOrganizationService;

    @GetMapping("/list")
    public ResponseEntity<List<CooperatingOrganizationResponse>> findAll() {
        return ResponseEntity.ok(cooperatingOrganizationService.findAll());
    }

    @PostMapping
    public ResponseEntity<CooperatingOrganizationResponse> create(@Valid @RequestBody CooperatingOrganizationRequest cooperatingOrganizationRequest) {
        return ResponseEntity.ok(cooperatingOrganizationService.create(cooperatingOrganizationRequest));
    }
}
