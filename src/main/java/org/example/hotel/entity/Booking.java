package org.example.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Booking {
    private String roomId;
    private Guest guest;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private boolean checkedOut = false;
    private LocalDate actualCheckOut;
}
