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
public class CarResponse {

    private Long id;
    private String title;
    private Long owner;
    private String subTitle;
    private Long carMake;
    private Long carModel;
    private Long fuelType;
    private Long transmission;
    private Long engineType;
    private Long drivetrainType;
    private boolean isApproved;
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
    private Boolean isPublic;
    private LocalDateTime createAt;
    private Integer numberOfDoors;
    private Integer numberOfSeats;
}
