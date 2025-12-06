package org.example.hotel.service;

import org.example.hotel.dto.HotelDTO;
import org.example.hotel.entity.Hotel;

import java.util.List;

public interface HotelService {

    public List<Hotel> searchByCity(String city);

    Hotel addHotel(HotelDTO hotel);

    Hotel updateHotel(Long id, HotelDTO hotelDTO);

    Hotel getHotelById(Long id);

    void deleteHotel(Long id);

    List<Hotel> getAllHotels();
}
