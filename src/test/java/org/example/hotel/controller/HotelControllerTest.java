package org.example.hotel.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.hotel.controller.RoomController;
import org.example.hotel.dto.RoomDTO;
import org.example.hotel.entity.Room;
import org.example.hotel.service.RoomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoomController.class)
public class HotelControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private RoomService roomService;

        @Autowired
        private ObjectMapper objectMapper;

        // ------------------ ADD ROOM ------------------
        @Test
        void addRoom_shouldReturnRoom() throws Exception {
            RoomDTO roomDTO = new RoomDTO();
            Room room = new Room();
            room.setId(1L);

            Mockito.when(roomService.saveRoom(Mockito.any(RoomDTO.class)))
                    .thenReturn(room);

            mockMvc.perform(post("/api/rooms/rooms")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(roomDTO)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1L));
        }

        // ------------------ GET ROOMS ------------------
        @Test
        void getRooms_shouldReturnRoomList() throws Exception {
            Room room = new Room();
            room.setId(1L);

            Mockito.when(roomService.findRooms(1L))
                    .thenReturn(List.of(room));

            mockMvc.perform(get("/api/rooms/rooms")
                            .param("hotelId", "1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id").value(1L));
        }

        // ------------------ UPDATE ROOM ------------------
        @Test
        void updateRoom_shouldReturnUpdatedRoom() throws Exception {
            RoomDTO roomDTO = new RoomDTO();
            Room room = new Room();
            room.setId(1L);

            Mockito.when(roomService.updateRoom(Mockito.eq(1L), Mockito.any(RoomDTO.class)))
                    .thenReturn(room);

            mockMvc.perform(put("/api/rooms/rooms/{id}", 1)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(roomDTO)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1L));
        }

        // ------------------ DELETE ROOM ------------------
        @Test
        void deleteRoom_shouldReturnNoContent() throws Exception {
            Mockito.doNothing().when(roomService).deleteRoom(1L);

            mockMvc.perform(delete("/api/rooms/rooms/{id}", 1))
                    .andExpect(status().isNoContent());
        }
}

