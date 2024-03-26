package com.automobile.pumba.data.transfer.response;

import com.automobile.pumba.data.transfer.model.CarTracking;
import com.automobile.pumba.data.transfer.model.ConditionType;
import com.automobile.pumba.data.transfer.model.MileageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDetailResponse {

    private Long id;
    private Long owner;
    private String title;
    private String subTitle;
    private CarMakeResponse carMake;
    private CarModelResponse carModel;
    private CarFuelTypeResponse fuelType;
    private CarTransmissionTypeResponse transmission;
    private CarEngineTypeResponse engineType;
    private CarDrivetrainTypeResponse drivetrainType;
    private ContactPhoneResponse contactPhone;
    private ContactEmailResponse contactEmail;
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
    private ConditionType conditionType;
    private String engineSize;
    private Boolean isBought;
    private Boolean isPublic;
    private Boolean isApproved;
    private LocalDateTime createAt;
    private Integer numberOfDoors;
    private Integer numberOfSeats;
}
