package am.automobile.pumba.web.endpoint;

import am.automobile.pumba.core.entity.Car;
import am.automobile.pumba.core.mapper.CarMapper;
import am.automobile.pumba.core.service.CarService;
import com.automobile.pumba.data.transfer.request.CarAdminFilterRequest;
import com.automobile.pumba.data.transfer.request.CarFilterRequest;
import com.automobile.pumba.data.transfer.request.CarRequest;
import com.automobile.pumba.data.transfer.response.CarDetailResponse;
import com.automobile.pumba.data.transfer.response.CarResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/car")
public class CarEndpoint {

    private final CarService carService;
    private final CarMapper carMapper;

    @PostMapping
    @PreAuthorize("hasAuthority('MANAGE_CAR_CREATE')")
    public ResponseEntity<CarResponse> createCar(@Valid @RequestBody CarRequest carRequest) {
        return ResponseEntity.ok(carService.createCar(carRequest));
    }

    @PutMapping("/{carId}")
    @PreAuthorize("hasAnyAuthority('MANAGE_CAR_UPDATE','MANAGE_ALL_CARS_UPDATE')")
    public ResponseEntity<CarResponse> editCar(@Valid @RequestBody CarRequest carRequest, @PathVariable long carId) {
        return ResponseEntity.ok(carService.editCar(carRequest, carId));
    }

    @PostAuthorize("isAuthenticated() and hasAuthority('MANAGE_CAR_CREATE')")
    @PostMapping("/image")
    public ResponseEntity<String> createCarImage(@RequestParam("image") MultipartFile file) {
        return ResponseEntity.ok(carService.saveImage(file));
    }

    @PostAuthorize("hasAuthority('MANAGE_CAR_APPROVE')")
    @PostMapping("/approve/{id}")
    public ResponseEntity<?> approve(@PathVariable long id) {
        return ResponseEntity.ok(carService.approveById(id));
    }

    @PostAuthorize("hasAuthority('MANAGE_CAR_CANCEL')")
    @PostMapping("/cancel/{id}")
    public ResponseEntity<?> cancelById(@PathVariable long id) {
        return ResponseEntity.ok(carService.cancelById(id));
    }

    @GetMapping("/image/{fileName}")
    public ResponseEntity<byte[]> getCarImage(@PathVariable String fileName) {
        byte[] image = carService.getImage(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(image.length)
                .body(image);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/admin/filter")
    public ResponseEntity<?> findAllAdminFilter(
            @PageableDefault(sort = {"createAt"}, direction = Sort.Direction.DESC) Pageable pageable,
            @RequestBody CarAdminFilterRequest carFilterRequest) {
        return ResponseEntity.ok(carService.findAllForAdmin(pageable, carFilterRequest));
    }

    @PostMapping("/filter")
    public ResponseEntity<?> findAllFilter(
            @PageableDefault(sort = {"createAt"}, direction = Sort.Direction.DESC) Pageable pageable,
            @RequestBody CarFilterRequest carFilterRequest) {
        return ResponseEntity.ok(carService.findAllFilter(pageable, carFilterRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDetailResponse> findById(@PathVariable Long id) {
        Car car = carService.findByIdAndAccess(id);
        return ResponseEntity.ok(carMapper.toResponseDetail(car));
    }

    @GetMapping("/images/details-url/{id}")
    public ResponseEntity<List<String>> findAllDetailsImagesByCarId(@PathVariable Long id) {
        return ResponseEntity.ok(carService.findAllImagesDetailUrlByCarId(id));
    }
}
