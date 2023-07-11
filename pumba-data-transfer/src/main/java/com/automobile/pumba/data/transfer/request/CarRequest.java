package com.automobile.pumba.data.transfer.request;

import com.automobile.pumba.data.transfer.model.CarTracking;
import com.automobile.pumba.data.transfer.model.ConditionType;
import com.automobile.pumba.data.transfer.model.MileageType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarRequest {

    @NotBlank
    private String title;
    private String subTitle;
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
    @Enumerated(EnumType.STRING)
    private MileageType mileageType;
    private BigDecimal price;
    private String vin;
    private String licensePlate;
    private String description;
    private String location;
    private String videoLink;
    private Boolean isBought;
    private Boolean isPublic;
    @NotBlank
    private String baseImage;
    private List<String> imagesDetails;
    @Enumerated(EnumType.STRING)
    private CarTracking tracking;
    @Enumerated(EnumType.STRING)
    private ConditionType conditionType;
    private String engineSize;
    private Integer numberOfDoors;
    private Integer numberOfSeats;
}
