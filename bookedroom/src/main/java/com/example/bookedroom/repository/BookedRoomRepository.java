package com.example.bookedroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookedroom.entity.BookedRoom;

public interface BookedRoomRepository extends JpaRepository<BookedRoom, Long> {
}
