package com.automobile.pumba.data.transfer.request;

import com.automobile.pumba.data.transfer.model.CarTracking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Stream;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarFilterRequest {

    private String keyword;
    private CarTracking tracking;
    private Integer drivetrainType;
    private Integer engineType;
    private Integer transmission;
    private Integer fuelType;
    private Integer carModel;
    private Integer carMake;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private BigDecimal minMileage;
    private BigDecimal maxMileage;
    private Integer year;

    public boolean isEmpty() {
        return Stream.of(
                keyword,
                tracking,
                drivetrainType,
                engineType,
                transmission,
                fuelType,
                carModel,
                carMake,
                minPrice,
                maxPrice,
                minMileage,
                maxMileage,
                year
        ).allMatch(Objects::isNull);
    }
}
