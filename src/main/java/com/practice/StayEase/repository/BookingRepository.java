package com.practice.StayEase.repository;

import com.practice.StayEase.entity.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BookingRepository extends CrudRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.hotelId = :hotelId AND b.guestEmail = :guestEmail")
    Booking findByEmailAndHotelId(@Param("hotelId") Long hotelId, @Param("guestEmail") String guestEmail);
}
