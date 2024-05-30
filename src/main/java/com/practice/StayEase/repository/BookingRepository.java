package com.practice.StayEase.repository;

import com.practice.StayEase.entity.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {
}
