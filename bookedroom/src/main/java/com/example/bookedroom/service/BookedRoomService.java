package com.example.bookedroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookedroom.entity.BookedRoom;
import com.example.bookedroom.entity.Room;
import com.example.bookedroom.feignclient.RoomFeignClient;
import com.example.bookedroom.repository.BookedRoomRepository;
import com.example.bookedroom.repository.RoomRepositry;

@Service
public class BookedRoomService {

    @Autowired
    private BookedRoomRepository bookedRoomRepository;
    
    @Autowired
    private RoomFeignClient roomClient;
    
    @Autowired
    private RoomRepositry roomRepositry;

    public List<BookedRoom> getAllBookedRooms() {
        return bookedRoomRepository.findAll();
    }

    public BookedRoom getBookedRoomById(Long id) {
        return bookedRoomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BookedRoom not found with id: " + id));
    }

    public BookedRoom bookRoom(BookedRoom bookedRoom,long roomId) {
        // Additional business logic can be added here before saving the booked room
    	Room room = roomClient.getRoomById(roomId);
    	bookedRoom.setRoom(room);
    	
        return bookedRoomRepository.save(bookedRoom);
    }

    public BookedRoom updateBookedRoom(Long id, BookedRoom updatedBookedRoom) {
        BookedRoom existingBookedRoom = getBookedRoomById(id);
        // Update existing booked room fields with the provided values
        existingBookedRoom.setCheckInDate(updatedBookedRoom.getCheckInDate());
        existingBookedRoom.setCheckOutDate(updatedBookedRoom.getCheckOutDate());
        existingBookedRoom.setGuestFullName(updatedBookedRoom.getGuestFullName());
        existingBookedRoom.setGuestEmail(updatedBookedRoom.getGuestEmail());
        // Update other fields as needed
        // Save the updated booked room
        return bookedRoomRepository.save(existingBookedRoom);
    }

    public void deleteBookedRoom(Long id) {
        // Check if booked room exists
        getBookedRoomById(id);
        // Delete the booked room
        bookedRoomRepository.deleteById(id);
    }
    
    
    public BookedRoom createRoom(BookedRoom bookedRoom,Long roomId) {
        // Additional business logic can be added here before saving the room
    	Room room = roomClient.getRoomById(roomId);
    	BookedRoom newBookedRoom = new BookedRoom();
    	newBookedRoom.setCheckInDate(bookedRoom.getCheckInDate());
    	newBookedRoom.setCheckOutDate(bookedRoom.getCheckOutDate());
    	newBookedRoom.setGuestFullName(bookedRoom.getGuestFullName());
    	newBookedRoom.setGuestEmail(bookedRoom.getGuestEmail());
    	newBookedRoom.setNumOfAdults(bookedRoom.getNumOfAdults());
    	newBookedRoom.setNumOfChildren(bookedRoom.getNumOfChildren());
    	newBookedRoom.setTotalNumOfGuest(bookedRoom.getNumOfAdults()+bookedRoom.getNumOfChildren());
    	newBookedRoom.setRoom(room);
    	
        return bookedRoomRepository.save(newBookedRoom);
    }
}

