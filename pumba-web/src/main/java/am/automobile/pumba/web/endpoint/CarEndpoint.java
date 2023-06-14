package am.automobile.pumba.web.endpoint;

import am.automobile.pumba.core.service.CarService;
import com.automobile.pumba.data.transfer.request.CarRequest;
import com.automobile.pumba.data.transfer.response.CarResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
}
