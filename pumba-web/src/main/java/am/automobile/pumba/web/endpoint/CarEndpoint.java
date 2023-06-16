package am.automobile.pumba.web.endpoint;

import am.automobile.pumba.core.service.CarService;
import com.automobile.pumba.data.transfer.request.CarAdminFilterRequest;
import com.automobile.pumba.data.transfer.request.CarRequest;
import com.automobile.pumba.data.transfer.response.CarResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/car")
public class CarEndpoint {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<CarResponse> createCar(@Valid @RequestBody CarRequest carRequest) {
        return ResponseEntity.ok(carService.createCar(carRequest));
    }

    @PostMapping("/image")
    public ResponseEntity<String> createCar(@RequestParam("image") MultipartFile file) {
        return ResponseEntity.ok(carService.saveImage(file));
    }

    @GetMapping("/image/{fileName}")
    public ResponseEntity<byte[]> createCar(@PathVariable String fileName) {
        return ResponseEntity.ok(carService.getImage(fileName));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/admin")
    public ResponseEntity<?> findAll(
            @PageableDefault(sort = {"createAt"}, direction = Sort.Direction.DESC) Pageable pageable,
            @RequestBody CarAdminFilterRequest carFilterRequest) {
        return ResponseEntity.ok(carService.findAllForAdmin(pageable, carFilterRequest));
    }
}
