package com.automobile.pumba.data.transfer.request;

import com.automobile.pumba.data.transfer.validation.MaxCurrentYear;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarFilterRequest {

    @Size(max = 100, message = "Keyword length must not exceed {max} characters.")
    private String keyword;
    private List<Integer> carModels;
    private List<Integer> carMakes;
    @Min(value = 1900, message = "Start year must be equal to or greater than {value}.")
    @MaxCurrentYear(message = "Start year must be equal to or less than current year.")
    private Integer startYear;
    @Min(value = 1900, message = "Start year must be equal to or greater than {value}.")
    @MaxCurrentYear(message = "Start year must be equal to or less than current year.")
    private Integer endYear;
    @Size(max = 50, message = "Color length must not exceed {max} characters.")
    private String color;
    @Min(value = 0, message = "Max mileage must be equal to or greater than {value}.")
    private Integer maxMileage;
    @Min(value = 0, message = "Min price must be equal to or greater than {value}.")
    private Integer minPrice;
    @Min(value = 1, message = "Max price must be equal to or greater than {value}.")
    private Integer maxPrice;
    private List<Integer> fuelTypes;
    private Integer numberOfDoors;
    @Min(1)
    @Max(300)
    private Integer numberOfSeats;
    private List<Integer> transmissions;
    private List<Integer> engineTypes;
    private List<Integer> drivetrainTypes;
    private Boolean inAuction;
    private Boolean hasArrived;
}
