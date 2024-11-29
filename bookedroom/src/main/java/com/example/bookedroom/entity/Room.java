package com.example.bookedroom.entity;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Entity
@Table(name ="")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room{
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
 
    @Column(nullable = false)
    private int roomNo;
 
    @Column(nullable = false)
    private double price;
 
  
    @Column(nullable = false)
    private String roomType;
 
    @Column(nullable = false)
    private boolean booked;
   
 
    
}