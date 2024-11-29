package com.example.bookedroom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookedroom.entity.BookedRoom;
import com.example.bookedroom.entity.Room;
import com.example.bookedroom.service.BookedRoomService;

@RestController
@RequestMapping("/bookedrooms")

@CrossOrigin("http://localhost:3000")
public class BookedRoomController {

    private final BookedRoomService bookedRoomService;

    @Autowired
    public BookedRoomController(BookedRoomService bookedRoomService) {
        this.bookedRoomService = bookedRoomService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<BookedRoom>> getAllBookedRooms() {
        List<BookedRoom> bookedRooms = bookedRoomService.getAllBookedRooms();
        return new ResponseEntity<>(bookedRooms, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookedRoom> getBookedRoomById(@PathVariable("id") Long id) {
        BookedRoom bookedRoom = bookedRoomService.getBookedRoomById(id);
        return new ResponseEntity<>(bookedRoom, HttpStatus.OK);
    }

    @PostMapping("/bookedrooms/create/{roomId}")
    public ResponseEntity<BookedRoom> bookRoom(@RequestBody BookedRoom bookedRoom, @PathVariable long roomId) {
        try {
            // Validate input data
            if (bookedRoom == null || bookedRoom.getRoom() == null || bookedRoom.getCheckInDate() == null ||
                    bookedRoom.getCheckOutDate() == null || bookedRoom.getNumOfAdults() < 1) {
                return ResponseEntity.badRequest().build();
            }

            // Attempt to book the room
            BookedRoom savedBookedRoom = bookedRoomService.bookRoom(bookedRoom,roomId);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBookedRoom);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Return an appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<BookedRoom> updateBookedRoom(@PathVariable("id") Long id, @RequestBody BookedRoom updatedBookedRoom) {
        BookedRoom updatedRoom = bookedRoomService.updateBookedRoom(id, updatedBookedRoom);
        return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  cancelBookedRoom(@PathVariable("id") Long id) {
        bookedRoomService.deleteBookedRoom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PostMapping("/createRoom/{roomId}")
    public ResponseEntity<BookedRoom> createRoom(@RequestBody BookedRoom bookedRoom,@PathVariable Long roomId) {
    	BookedRoom createdRoom = bookedRoomService.createRoom(bookedRoom,roomId);
        return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);
    }
}
