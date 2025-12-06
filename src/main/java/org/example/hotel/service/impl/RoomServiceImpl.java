package org.example.hotel.service.impl;

import org.example.hotel.dto.RoomDTO;
import org.example.hotel.entity.Hotel;
import org.example.hotel.entity.Room;
import org.example.hotel.repository.RoomRepository;
import org.example.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    public RoomRepository roomRepository;

    @Override
    public Room saveRoom(RoomDTO roomDto) {
        Room room = createRoom(roomDto);
        return roomRepository.save(room);
    }

    @Override
    public List<Room> findRooms(long hotelId) {
        return roomRepository.findByHotelId(hotelId);
    }

    @Override
    public Room updateRoom(Long id, RoomDTO roomDTO) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        room.setRoomType(roomDTO.getRoomType());
        room.setTotalRooms(roomDTO.getTotalRooms());
        room.setPricePerNight(roomDTO.getPricePerNight());
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Long id) {
        Room room = roomRepository.findById(id).get();
        roomRepository.delete(room);
    }

    private Room createRoom(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoomType(roomDTO.getRoomType());
        room.setTotalRooms(roomDTO.getTotalRooms());
        room.setHotelId(roomDTO.getHotelId());
        room.setPricePerNight(roomDTO.getPricePerNight());
        return room;
    }

}
