package com.automobile.pumba.data.transfer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarMetadataResponse {

    private List<CarDrivetrainTypeResponse> carDrivetrainTypes;
    private List<CarEngineTypeResponse> carEngineTypes;
    private List<CarFuelTypeResponse> carFuelTypes;
    private List<CarMakeResponse> carMakes;
    private List<CarModelResponse> carModels;
    private List<CarTransmissionTypeResponse> carTransmissionTypes;
}
