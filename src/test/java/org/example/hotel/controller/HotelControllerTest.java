package org.example.hotel.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.hotel.controller.RoomController;
import org.example.hotel.dto.HotelDTO;
import org.example.hotel.dto.RoomDTO;
import org.example.hotel.entity.Hotel;
import org.example.hotel.entity.Room;
import org.example.hotel.service.HotelService;
import org.example.hotel.service.RoomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HotelController.class)
public class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelService hotelService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addHotel_shouldReturnHotel() throws Exception {
        HotelDTO dto = new HotelDTO();
        Hotel hotel = new Hotel();
        hotel.setId(1L);


        Mockito.when(hotelService.addHotel(Mockito.any(HotelDTO.class)))
                .thenReturn(hotel);

        mockMvc.perform(post("/api/hotels/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void search_shouldReturnHotels() throws Exception {
        when(hotelService.searchByCity("Paris"))
                .thenReturn(List.of(new Hotel()));

        mockMvc.perform(get("/api/hotels/search")
                        .param("city", "Paris"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllHotels_shouldReturnList() throws Exception {
        when(hotelService.getAllHotels())
                .thenReturn(List.of(new Hotel()));

        mockMvc.perform(get("/api/hotels/hotels"))
                .andExpect(status().isOk());
    }

    @Test
    void getHotelById_shouldReturnHotel() throws Exception {
        when(hotelService.getHotelById(1L))
                .thenReturn(new Hotel());

        mockMvc.perform(get("/api/hotels/hotels/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void updateHotel_shouldReturnUpdatedHotel() throws Exception {
        when(hotelService.updateHotel(eq(1L), any()))
                .thenReturn(new Hotel());

        mockMvc.perform(put("/api/hotels/hotels/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new HotelDTO())))
                .andExpect(status().isOk());
    }

    @Test
    void deleteHotel_shouldReturnNoContent() throws Exception {
        doNothing().when(hotelService).deleteHotel(1L);

        mockMvc.perform(delete("/api/hotels/hotels/{id}", 1))
                .andExpect(status().isNoContent());
    }
}

