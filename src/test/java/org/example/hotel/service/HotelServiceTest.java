package org.example.hotel.service;
import org.example.hotel.dto.HotelDTO;
import org.example.hotel.entity.Hotel;
import org.example.hotel.repository.HotelRepository;
import org.example.hotel.service.impl.HotelServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {
    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelServiceImpl hotelService;

    private HotelDTO hotelDTO;
    private Hotel hotel;

    @BeforeEach
    void setUp() {
        hotelDTO = new HotelDTO();
        hotelDTO.setName("Grand Hotel");
        hotelDTO.setCity("Paris");
        hotelDTO.setAddress("123 Street");
        hotelDTO.setRating(5.0);

        hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("Grand Hotel");
        hotel.setCity("Paris");
        hotel.setAddress("123 Street");
        hotel.setRating(5.0);
    }

    // ---------------- SEARCH BY CITY ----------------
    @Test
    void searchByCity_shouldReturnHotels() {
        when(hotelRepository.findByCityIgnoreCase("Paris"))
                .thenReturn(List.of(hotel));

        List<Hotel> result = hotelService.searchByCity("Paris");

        assertEquals(1, result.size());
        verify(hotelRepository).findByCityIgnoreCase("Paris");
    }

    // ---------------- ADD HOTEL ----------------
    @Test
    void addHotel_shouldSaveAndReturnHotel() {
        when(hotelRepository.save(any(Hotel.class)))
                .thenReturn(hotel);

        Hotel result = hotelService.addHotel(hotelDTO);

        assertNotNull(result);
        assertEquals("Grand Hotel", result.getName());
        verify(hotelRepository).save(any(Hotel.class));
    }

    // ---------------- UPDATE HOTEL ----------------
    @Test
    void updateHotel_shouldUpdateAndReturnHotel() {
        when(hotelRepository.findById(1L))
                .thenReturn(Optional.of(hotel));
        when(hotelRepository.save(any(Hotel.class)))
                .thenReturn(hotel);

        Hotel result = hotelService.updateHotel(1L, hotelDTO);

        assertEquals("Grand Hotel", result.getName());
        verify(hotelRepository).findById(1L);
        verify(hotelRepository).save(hotel);
    }

    // ---------------- GET HOTEL BY ID ----------------
    @Test
    void getHotelById_shouldReturnHotel() {
        when(hotelRepository.findById(1L))
                .thenReturn(Optional.of(hotel));

        Hotel result = hotelService.getHotelById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void getHotelById_shouldThrowException_whenNotFound() {
        when(hotelRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> hotelService.getHotelById(1L));
    }

    // ---------------- DELETE HOTEL ----------------
    @Test
    void deleteHotel_shouldDeleteHotel() {
        when(hotelRepository.findById(1L))
                .thenReturn(Optional.of(hotel));

        hotelService.deleteHotel(1L);

        verify(hotelRepository).delete(hotel);
    }

    // ---------------- GET ALL HOTELS ----------------
    @Test
    void getAllHotels_shouldReturnList() {
        when(hotelRepository.findAll())
                .thenReturn(List.of(hotel));

        List<Hotel> result = hotelService.getAllHotels();

        assertEquals(1, result.size());
        verify(hotelRepository).findAll();
    }
}
