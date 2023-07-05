package com.automobile.pumba.data.transfer.request;

import com.automobile.pumba.data.transfer.model.CarTracking;
import com.automobile.pumba.data.transfer.model.ConditionType;
import com.automobile.pumba.data.transfer.model.MileageType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
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
    @Min(value = 1)
    private Long carMake;
    @Min(value = 1)
    private Long carModel;
    @Min(value = 1)
    private Long fuelType;
    @Min(value = 1)
    private Long transmission;
    @Min(value = 1)
    private Long engineType;
    @Min(value = 1)
    private Long drivetrainType;
    @Min(value = 1)
    private Long contactPhone;
    @Min(value = 1)
    private Long contactEmail;
    @Min(value = 1900, message = "Start year must be equal to or greater than {value}.")
    private Integer year;
    private String color;
    @Min(value = 1)
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
    private Boolean isPublic;
    @NotBlank
    private String baseImage;
    private List<String> imagesDetails;
    @Enumerated(EnumType.STRING)
    private CarTracking tracking;
    @Enumerated(EnumType.STRING)
    private ConditionType conditionType;
    private String engineSize;
    @Min(value = 1)
    private Integer numberOfDoors;
    private Integer numberOfSeats;
}
