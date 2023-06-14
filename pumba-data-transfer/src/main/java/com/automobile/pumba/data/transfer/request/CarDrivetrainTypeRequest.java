package com.automobile.pumba.data.transfer.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDrivetrainTypeRequest {

    @NotBlank
    private String name;
    private String description;
}
