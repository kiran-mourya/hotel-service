package org.example.hotel.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class HotelDTO {
    private String name;
    private String city;
    private String address;
    private Double rating;
}
