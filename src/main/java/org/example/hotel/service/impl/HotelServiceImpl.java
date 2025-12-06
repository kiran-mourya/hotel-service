package org.example.hotel.service.impl;

import org.example.hotel.dto.HotelDTO;
import org.example.hotel.entity.Hotel;
import org.example.hotel.repository.HotelRepository;
import org.example.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<Hotel> searchByCity(String city) {
        return hotelRepository.findByCityIgnoreCase(city);
    }

    @Override
    public Hotel addHotel(HotelDTO hotelDTO) {
        Hotel hotel = createHotel(hotelDTO);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel updateHotel(Long id, HotelDTO hotelDTO) {
        Hotel hotel = getHotelById(id);
        hotel.setName(hotelDTO.getName());
        hotel.setCity(hotelDTO.getCity());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setRating(hotelDTO.getRating());
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteHotel(Long id) {
        Hotel hotel = getHotelById(id);
        hotelRepository.delete(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    private Hotel createHotel(HotelDTO hotelDTO) {
        System.out.println(hotelDTO.getName() + "   " + hotelDTO.getRating());
        Hotel hotel = new Hotel();
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setCity(hotelDTO.getCity());
        hotel.setName(hotelDTO.getName());
        hotel.setRating(hotelDTO.getRating());

        return hotel;

    }


}
