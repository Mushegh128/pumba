package com.automobile.pumba.data.transfer.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CooperatingOrganizationRequest {

    @NotBlank(message = "Name is required")
    private String name;
    @NotNull(message = "Logo is required")
    private MultipartFile logo;
    private String description;
    private String website;
}
