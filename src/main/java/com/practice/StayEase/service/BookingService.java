package com.practice.StayEase.service;

import com.practice.StayEase.dto.Email;
import com.practice.StayEase.entity.Booking;
import com.practice.StayEase.exception.BookingNotFoundException;
import com.practice.StayEase.exception.HotelNotfoundException;
import com.practice.StayEase.exception.RoomNotAvailableException;

public interface BookingService {

    Booking makeBooking(Long hotelId, Email userEmail) throws HotelNotfoundException, RoomNotAvailableException;
    void deleteBooking(Long BookingId) throws BookingNotFoundException;
}
