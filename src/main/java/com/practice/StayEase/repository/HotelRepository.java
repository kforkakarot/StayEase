package com.practice.StayEase.repository;

import com.practice.StayEase.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
