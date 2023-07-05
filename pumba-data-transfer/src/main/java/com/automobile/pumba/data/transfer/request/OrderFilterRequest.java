package com.automobile.pumba.data.transfer.request;

import com.automobile.pumba.data.transfer.model.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.stream.Stream;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderFilterRequest {

    private Long manager;
    private Long car;
    private String firstName;
    private String lastName;
    private String companyName;
    private String country;
    private String streetAddress;
    private String address;
    private String postcodeZip;
    private String city;
    private String province;
    private String phoneNumber;
    private String email;
    private String ipAddress;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public boolean isEmpty() {
        return Stream.of(
                manager,
                car,
                firstName,
                lastName,
                companyName,
                country,
                streetAddress,
                address,
                postcodeZip,
                city,
                province,
                phoneNumber,
                email,
                ipAddress,
                orderStatus
        ).allMatch(Objects::isNull);
    }

}
