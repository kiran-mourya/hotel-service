package org.example.hotel.service;

import org.example.hotel.dto.RoomDTO;
import org.example.hotel.entity.Room;

import java.util.List;

public interface RoomService {

    public Room saveRoom(RoomDTO room);

    public List<Room> findRooms(long hotelId);

    Room updateRoom(Long id, RoomDTO roomDTO);

    void deleteRoom(Long id);
}
