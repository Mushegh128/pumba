package am.automobile.pumba.core.service;

import am.automobile.pumba.core.entity.CarDrivetrainType;
import am.automobile.pumba.core.entity.CarEngineType;
import am.automobile.pumba.core.entity.CarFuelType;
import am.automobile.pumba.core.entity.CarMake;
import am.automobile.pumba.core.entity.CarModel;
import am.automobile.pumba.core.entity.CarTransmissionType;
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

import java.util.List;

public interface CarMetaDataService {


    CarDrivetrainTypeResponse createCarDrivetrainType(CarDrivetrainTypeRequest carDrivetrainTypeRequest);

    CarEngineTypeResponse createCarEngineType(CarEngineTypeRequest carEngineTypeRequest);

    CarMakeResponse createCarMake(CarMakeRequest carMakeRequest);

    CarModelResponse createCarModel(CarModelRequest carModelRequest);

    CarFuelTypeResponse createCarFuelType(CarFuelTypeRequest carFuelTypeRequest);

    CarTransmissionTypeResponse createCarTransmissionType(CarTransmissionTypeRequest carTransmissionTypeRequest);

    List<CarDrivetrainTypeResponse> findAllCarDrivetrainTypes();

    List<CarEngineTypeResponse> findAllCarEngineTypes();

    List<CarMakeResponse> findAllCarMakes();

    List<CarModelResponse> findAllCarModels();

    List<CarFuelTypeResponse> findAllCarFuelTypes();

    List<CarTransmissionTypeResponse> findAllCarTransmissionTypes();

    CarMetadataResponse findAll();

    CarDrivetrainType findCarDrivetrainTypeById(long id);

    CarEngineType findCarEngineTypeById(long id);

    CarMake findCarMakeById(long id);

    CarModel findCarModelById(long id);

    CarFuelType findCarFuelTypeById(long id);

    CarTransmissionType findCarTransmissionTypeById(long id);

    void deleteCarDrivetrainTypeById(long id);

    void deleteCarEngineTypeById(long id);

    void deleteCarMakeById(long id);

    void deleteCarModelById(long id);

    void deleteCarFuelTypeById(long id);

    void deleteCarTransmissionTypeById(long id);

    CarDrivetrainTypeResponse updateCarDrivetrainType(long id, CarDrivetrainTypeRequest carDrivetrainTypeRequest);

    CarEngineTypeResponse updateCarEngineType(long id, CarEngineTypeRequest carEngineTypeRequest);

    CarMakeResponse updateCarMake(long id, CarMakeRequest carMakeRequest);

    CarModelResponse updateCarModel(long id, CarModelRequest carModelRequest);

    CarFuelTypeResponse updateCarFuelType(long id, CarFuelTypeRequest carFuelTypeRequest);

    CarTransmissionTypeResponse updateCarTransmissionType(long id, CarTransmissionTypeRequest carTransmissionTypeRequest);
}
