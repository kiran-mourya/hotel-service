package org.example.hotel.service;

import org.example.hotel.dto.RoomDTO;
import org.example.hotel.entity.Room;
import org.example.hotel.repository.RoomRepository;
import org.example.hotel.service.impl.RoomServiceImpl;
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
class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;   // ✅ Mock interface

    @InjectMocks
    private RoomServiceImpl roomService;     // ✅ Class under test

    private RoomDTO roomDTO;
    private Room room;

    @BeforeEach
    void setUp() {
        roomDTO = new RoomDTO();
        roomDTO.setHotelId(1L);
        roomDTO.setRoomType("DELUXE");
        roomDTO.setTotalRooms(10);
        roomDTO.setPricePerNight(2500.0);

        room = new Room();
        room.setId(1L);
        room.setHotelId(1L);
        room.setRoomType("DELUXE");
        room.setTotalRooms(10);
        room.setPricePerNight(2500.0);
    }

    // ---------------- SAVE ROOM ----------------
    @Test
    void saveRoom_shouldSaveAndReturnRoom() {
        when(roomRepository.save(any(Room.class)))
                .thenReturn(room);

        Room result = roomService.saveRoom(roomDTO);

        assertNotNull(result);
        assertEquals("DELUXE", result.getRoomType());
        verify(roomRepository).save(any(Room.class));
    }

    // ---------------- FIND ROOMS ----------------
    @Test
    void findRooms_shouldReturnRoomList() {
        when(roomRepository.findByHotelId(1L))
                .thenReturn(List.of(room));

        List<Room> result = roomService.findRooms(1L);

        assertEquals(1, result.size());
        verify(roomRepository).findByHotelId(1L);
    }

    // ---------------- UPDATE ROOM ----------------
    @Test
    void updateRoom_shouldUpdateAndReturnRoom() {
        when(roomRepository.findById(1L))
                .thenReturn(Optional.of(room));
        when(roomRepository.save(any(Room.class)))
                .thenReturn(room);

        Room result = roomService.updateRoom(1L, roomDTO);

        assertEquals("DELUXE", result.getRoomType());
        assertEquals(2500.0, result.getPricePerNight());
        verify(roomRepository).save(room);
    }

    @Test
    void updateRoom_shouldThrowException_whenRoomNotFound() {
        when(roomRepository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> roomService.updateRoom(1L, roomDTO)
        );

        assertEquals("Room not found", exception.getMessage());
    }

    // ---------------- DELETE ROOM ----------------
    @Test
    void deleteRoom_shouldDeleteRoom() {
        when(roomRepository.findById(1L))
                .thenReturn(Optional.of(room));

        roomService.deleteRoom(1L);

        verify(roomRepository).delete(room);
    }
}
