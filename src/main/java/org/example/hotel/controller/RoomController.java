package org.example.hotel.controller;

import org.example.hotel.dto.RoomDTO;
import org.example.hotel.entity.Room;
import org.example.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/rooms")
    public Room addRoom(@RequestBody RoomDTO roomDTO){
        return roomService.saveRoom(roomDTO);
    }

    @GetMapping("/rooms")
    public List<Room> addRoom(@RequestParam long hotelId){
        return roomService.findRooms(hotelId);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody RoomDTO roomDTO) {
        return ResponseEntity.ok(roomService.updateRoom(id, roomDTO));
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }


}
