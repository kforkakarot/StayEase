package com.practice.StayEase.controller;

import com.practice.StayEase.dto.Email;
import com.practice.StayEase.dto.ErrorResponse;
import com.practice.StayEase.dto.SuccessMessage;
import com.practice.StayEase.entity.Booking;
import com.practice.StayEase.exception.BookingNotFoundException;
import com.practice.StayEase.exception.HotelNotfoundException;
import com.practice.StayEase.exception.RoomNotAvailableException;
import com.practice.StayEase.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/booking")
public class BookingController {

    private final BookingService bookingService;

    //Book a room in a hotel
    @PostMapping("/hotels/{hotelId}/book")
    public ResponseEntity<Booking> bookHotelRoom (@PathVariable Long hotelId, @RequestBody Email guestEmail){
        try{
            return new ResponseEntity(bookingService.makeBooking(hotelId,guestEmail), HttpStatus.OK);
        }catch(HotelNotfoundException e){
            log.error(e.getMessage());
            return  new ResponseEntity(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }catch(RoomNotAvailableException e){
            log.error(e.getMessage());
            return  new ResponseEntity(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    // delete a booking in a hotel
    @DeleteMapping("/bookings/{bookingId}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long bookingId){
        try{
            bookingService.deleteBooking(bookingId);
            return new ResponseEntity(new SuccessMessage("Booking Deleted Successfully"), HttpStatus.OK);
        }catch(BookingNotFoundException e){
            log.error(e.getMessage());
            return  new ResponseEntity(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }


}
