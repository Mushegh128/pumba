package am.automobile.pumba.web.endpoint;

import am.automobile.pumba.core.service.CarMetaDataService;
import com.automobile.pumba.data.transfer.request.CarDrivetrainTypeRequest;
import com.automobile.pumba.data.transfer.request.CarEngineTypeRequest;
import com.automobile.pumba.data.transfer.request.CarFuelTypeRequest;
import com.automobile.pumba.data.transfer.request.CarMakeRequest;
import com.automobile.pumba.data.transfer.request.CarModelRequest;
import com.automobile.pumba.data.transfer.request.CarTransmissionTypeRequest;
import com.automobile.pumba.data.transfer.response.CarDrivetrainTypeResponse;
import com.automobile.pumba.data.transfer.response.CarEngineTypeResponse;
import com.automobile.pumba.data.transfer.response.CarFuelTypeResponse;
import com.automobile.pumba.data.transfer.response.CarMakeResponse;
import com.automobile.pumba.data.transfer.response.CarMetadataResponse;
import com.automobile.pumba.data.transfer.response.CarModelResponse;
import com.automobile.pumba.data.transfer.response.CarTransmissionTypeResponse;
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

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/car/metadata")
public class CarMetadataEndpoint {

    private final CarMetaDataService carMetaDataService;

    @PostMapping("/drivetrain")
    public ResponseEntity<CarDrivetrainTypeResponse> createCarDrivetrainType(@Valid @RequestBody CarDrivetrainTypeRequest carDrivetrainTypeRequest) {
        return ResponseEntity.ok(carMetaDataService.createCarDrivetrainType(carDrivetrainTypeRequest));
    }

    @PostMapping("/engine")
    public ResponseEntity<CarEngineTypeResponse> createCarEngineType(@Valid @RequestBody CarEngineTypeRequest carEngineTypeRequest) {
        return ResponseEntity.ok(carMetaDataService.createCarEngineType(carEngineTypeRequest));
    }

    @PostMapping("/make")
    public ResponseEntity<CarMakeResponse> createCarMake(@Valid @RequestBody CarMakeRequest carMakeRequest) {
        return ResponseEntity.ok(carMetaDataService.createCarMake(carMakeRequest));
    }

    @PostMapping("/model")
    public ResponseEntity<CarModelResponse> createCarModel(@Valid @RequestBody CarModelRequest carModelRequest) {
        return ResponseEntity.ok(carMetaDataService.createCarModel(carModelRequest));
    }

    @PostMapping("/fuel-type")
    public ResponseEntity<CarFuelTypeResponse> createCarFuelType(@Valid @RequestBody CarFuelTypeRequest carFuelTypeRequest) {
        return ResponseEntity.ok(carMetaDataService.createCarFuelType(carFuelTypeRequest));
    }

    @PostMapping("/transmission")
    public ResponseEntity<CarTransmissionTypeResponse> createCarTransmissionType(@Valid @RequestBody CarTransmissionTypeRequest carTransmissionTypeRequest) {
        return ResponseEntity.ok(carMetaDataService.createCarTransmissionType(carTransmissionTypeRequest));
    }

    @GetMapping({"", "/"})
    public ResponseEntity<CarMetadataResponse> findAll() {
        return ResponseEntity.ok(carMetaDataService.findAll());
    }

    @GetMapping("/drivetrain")
    public ResponseEntity<List<CarDrivetrainTypeResponse>> findAllCarDrivetrainTypes() {
        return ResponseEntity.ok(carMetaDataService.findAllCarDrivetrainTypes());
    }

    @GetMapping("/engine")
    public ResponseEntity<List<CarEngineTypeResponse>> findAllCarEngineTypes() {
        return ResponseEntity.ok(carMetaDataService.findAllCarEngineTypes());
    }

    @GetMapping("/make")
    public ResponseEntity<List<CarMakeResponse>> findAllCarMakes() {
        return ResponseEntity.ok(carMetaDataService.findAllCarMakes());
    }

    @GetMapping("/model")
    public ResponseEntity<List<CarModelResponse>> findAllCarModels() {
        return ResponseEntity.ok(carMetaDataService.findAllCarModels());
    }

    @GetMapping("/fuel-type")
    public ResponseEntity<List<CarFuelTypeResponse>> findAllCarFuelTypes() {
        return ResponseEntity.ok(carMetaDataService.findAllCarFuelTypes());
    }

    @GetMapping("/transmission")
    public ResponseEntity<List<CarTransmissionTypeResponse>> findAllCarTransmissionTypes() {
        return ResponseEntity.ok(carMetaDataService.findAllCarTransmissionTypes());
    }
}
