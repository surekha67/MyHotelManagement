package com.example.bookedroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookedroom.entity.Room;

@Repository
public interface RoomRepositry  extends JpaRepository<Room, Long>{

}
