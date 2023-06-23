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
import org.springframework.security.access.prepost.PreAuthorize;
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
@CrossOrigin("*")
@RequestMapping("/car/metadata")
public class CarMetadataEndpoint {

    private final CarMetaDataService carMetaDataService;

    @PostMapping("/drivetrain")
    @PreAuthorize("hasAuthority('MANAGE_CAR_METADATA_CREATE')")
    public ResponseEntity<CarDrivetrainTypeResponse> createCarDrivetrainType(@Valid @RequestBody CarDrivetrainTypeRequest carDrivetrainTypeRequest) {
        return ResponseEntity.ok(carMetaDataService.createCarDrivetrainType(carDrivetrainTypeRequest));
    }

    @DeleteMapping("/drivetrain/{id}")
    @PreAuthorize("hasAuthority('MANAGE_CAR_METADATA_DELETE')")
    public ResponseEntity<?> deleteCarDrivetrainType(@PathVariable long id) {
        carMetaDataService.deleteCarDrivetrainTypeById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/drivetrain/{id}")
    @PreAuthorize("hasAuthority('MANAGE_CAR_METADATA_UPDATE')")
    public ResponseEntity<?> updateCarDrivetrainType(@PathVariable long id, @Valid @RequestBody CarDrivetrainTypeRequest carDrivetrainTypeRequest) {
        return ResponseEntity.ok(carMetaDataService.updateCarDrivetrainType(id, carDrivetrainTypeRequest));
    }

    @GetMapping("/drivetrain")
    public ResponseEntity<List<CarDrivetrainTypeResponse>> findAllCarDrivetrainTypes() {
        return ResponseEntity.ok(carMetaDataService.findAllCarDrivetrainTypes());
    }

    @PostMapping("/engine")
    @PreAuthorize("hasAuthority('MANAGE_CAR_METADATA_CREATE')")
    public ResponseEntity<CarEngineTypeResponse> createCarEngineType(@Valid @RequestBody CarEngineTypeRequest carEngineTypeRequest) {
        return ResponseEntity.ok(carMetaDataService.createCarEngineType(carEngineTypeRequest));
    }

    @DeleteMapping("/engine/{id}")
    @PreAuthorize("hasAuthority('MANAGE_CAR_METADATA_DELETE')")
    public ResponseEntity<?> deleteEngine(@PathVariable long id) {
        carMetaDataService.deleteCarEngineTypeById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/engine/{id}")
    @PreAuthorize("hasAuthority('MANAGE_CAR_METADATA_UPDATE')")
    public ResponseEntity<?> updateEngine(@PathVariable long id, @Valid @RequestBody CarEngineTypeRequest carEngineTypeRequest) {
        return ResponseEntity.ok(carMetaDataService.updateCarEngineType(id, carEngineTypeRequest));
    }

    @GetMapping("/engine")
    public ResponseEntity<List<CarEngineTypeResponse>> findAllEngine() {
        return ResponseEntity.ok(carMetaDataService.findAllCarEngineTypes());
    }

    @PostMapping("/make")
    @PreAuthorize("hasAuthority('MANAGE_CAR_METADATA_CREATE')")
    public ResponseEntity<CarMakeResponse> createCarMake(@Valid @RequestBody CarMakeRequest carMakeRequest) {
        return ResponseEntity.ok(carMetaDataService.createCarMake(carMakeRequest));
    }

    @DeleteMapping("/make/{id}")
    @PreAuthorize("hasAuthority('MANAGE_CAR_METADATA_DELETE')")
    public ResponseEntity<?> deleteMake(@PathVariable long id) {
        carMetaDataService.deleteCarMakeById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/make/{id}")
    @PreAuthorize("hasAuthority('MANAGE_CAR_METADATA_UPDATE')")
    public ResponseEntity<?> updateMake(@PathVariable long id, @Valid @RequestBody CarMakeRequest carMakeRequest) {
        return ResponseEntity.ok(carMetaDataService.updateCarMake(id, carMakeRequest));
    }

    @GetMapping("/make")
    public ResponseEntity<List<CarMakeResponse>> findAllCarMakes() {
        return ResponseEntity.ok(carMetaDataService.findAllCarMakes());
    }

    @PostMapping("/model")
    @PreAuthorize("hasAuthority('MANAGE_CAR_METADATA_CREATE')")
    public ResponseEntity<CarModelResponse> createCarModel(@Valid @RequestBody CarModelRequest carModelRequest) {
        return ResponseEntity.ok(carMetaDataService.createCarModel(carModelRequest));
    }

    @GetMapping("/model")
    public ResponseEntity<List<CarModelResponse>> findAllCarModels() {
        return ResponseEntity.ok(carMetaDataService.findAllCarModels());
    }

    @DeleteMapping("/model/{id}")
    @PreAuthorize("hasAuthority('MANAGE_CAR_METADATA_DELETE')")
    public ResponseEntity<?> deleteModel(@PathVariable long id) {
        carMetaDataService.deleteCarModelById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/model/{id}")
    @PreAuthorize("hasAuthority('MANAGE_CAR_METADATA_UPDATE')")
    public ResponseEntity<?> updateModel(@PathVariable long id, @Valid @RequestBody CarModelRequest carModelRequest) {
        return ResponseEntity.ok(carMetaDataService.updateCarModel(id, carModelRequest));
    }

    @PostMapping("/fuel-type")
    @PreAuthorize("hasAuthority('MANAGE_CAR_METADATA_CREATE')")
    public ResponseEntity<CarFuelTypeResponse> createCarFuelType(@Valid @RequestBody CarFuelTypeRequest carFuelTypeRequest) {
        return ResponseEntity.ok(carMetaDataService.createCarFuelType(carFuelTypeRequest));
    }

    @GetMapping("/fuel-type")
    public ResponseEntity<List<CarFuelTypeResponse>> findAllCarFuelTypes() {
        return ResponseEntity.ok(carMetaDataService.findAllCarFuelTypes());
    }

    @DeleteMapping("/fuel-type/{id}")
    @PreAuthorize("hasAuthority('MANAGE_CAR_METADATA_DELETE')")
    public ResponseEntity<?> deleteFuelType(@PathVariable long id) {
        carMetaDataService.deleteCarFuelTypeById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/fuel-type/{id}")
    @PreAuthorize("hasAuthority('MANAGE_CAR_METADATA_UPDATE')")
    public ResponseEntity<?> updateFuelType(@PathVariable long id, @Valid @RequestBody CarFuelTypeRequest carFuelTypeRequest) {
        return ResponseEntity.ok(carMetaDataService.updateCarFuelType(id, carFuelTypeRequest));
    }

    @PostMapping("/transmission")
    @PreAuthorize("hasAuthority('MANAGE_CAR_METADATA_CREATE')")
    public ResponseEntity<CarTransmissionTypeResponse> createCarTransmissionType(@Valid @RequestBody CarTransmissionTypeRequest carTransmissionTypeRequest) {
        return ResponseEntity.ok(carMetaDataService.createCarTransmissionType(carTransmissionTypeRequest));
    }

    @GetMapping("/transmission")
    public ResponseEntity<List<CarTransmissionTypeResponse>> findAllCarTransmissionTypes() {
        return ResponseEntity.ok(carMetaDataService.findAllCarTransmissionTypes());
    }

    @DeleteMapping("/transmission/{id}")
    @PreAuthorize("hasAuthority('MANAGE_CAR_METADATA_DELETE')")
    public ResponseEntity<?> deleteTransmission(@PathVariable long id) {
        carMetaDataService.deleteCarTransmissionTypeById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/transmission/{id}")
    @PreAuthorize("hasAuthority('MANAGE_CAR_METADATA_UPDATE')")
    public ResponseEntity<?> updateTransmission(@PathVariable long id, @Valid @RequestBody CarTransmissionTypeRequest carTransmissionTypeRequest) {
        return ResponseEntity.ok(carMetaDataService.updateCarTransmissionType(id, carTransmissionTypeRequest));
    }

    @GetMapping({"", "/"})
    public ResponseEntity<CarMetadataResponse> findAll() {
        return ResponseEntity.ok(carMetaDataService.findAll());
    }

}
