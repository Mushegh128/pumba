package com.automobile.pumba.data.transfer.response;

import com.automobile.pumba.data.transfer.model.CarTracking;
import com.automobile.pumba.data.transfer.model.MileageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarResponse {

    private String title;
    private Long carMake;
    private Long carModel;
    private Long fuelType;
    private Long transmission;
    private Long engineType;
    private Long drivetrainType;
    private Long contactPhone;
    private Long contactEmail;
    private Integer year;
    private String color;
    private Integer cylinders;
    private Integer mileage;
    private MileageType mileageType;
    private BigDecimal price;
    private String vin;
    private String licensePlate;
    private String description;
    private String location;
    private String videoLink;
    private String baseImage;
    private CarTracking tracking;
    private Integer numberOfDoors;
    private Integer numberOfSeats;
}
