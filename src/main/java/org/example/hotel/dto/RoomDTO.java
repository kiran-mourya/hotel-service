package org.example.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private Long hotelId;
    private String roomType;// Single/Double/Suite
    private Double pricePerNight;
    private Integer totalRooms;

}
