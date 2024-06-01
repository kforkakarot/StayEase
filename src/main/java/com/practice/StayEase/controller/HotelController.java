package com.practice.StayEase.controller;

import com.practice.StayEase.dto.ErrorResponse;
import com.practice.StayEase.dto.SuccessMessage;
import com.practice.StayEase.entity.Hotel;
import com.practice.StayEase.exception.HotelNotfoundException;
import com.practice.StayEase.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hotel")
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/getAllHotels")
    public ResponseEntity<List<Hotel>> getAllHotel(){
        return new ResponseEntity (hotelService.getAllHotel(), HttpStatus.OK);
    }

    @GetMapping("/getHotelById/{id}")
    public ResponseEntity<?> getHotelById(@PathVariable Long id){
        try{
            return new ResponseEntity(hotelService.findHotelById(id), HttpStatus.OK);
        }catch(HotelNotfoundException e){
            log.error(e.getMessage());
            return  new ResponseEntity(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addNewHotel")
    public ResponseEntity<Hotel> addNewHotel(@RequestBody Hotel hotel){
        return new ResponseEntity(hotelService.addNewHotel(hotel),HttpStatus.OK);
    }

    @DeleteMapping("/deleteHotelById/{id}")
    public ResponseEntity<?> deleteHotelById(@PathVariable Long id){
        try{
            hotelService.deleteHotelById(id);
            return new ResponseEntity(new SuccessMessage("Deleted SuccessFully"), HttpStatus.OK);
        }catch(HotelNotfoundException e){
            log.error(e.getMessage());
            return new ResponseEntity(new ErrorResponse(e.getMessage()),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateHotelById/{id}")
    public ResponseEntity<?> updateHotelById(@RequestBody Hotel hotel, @PathVariable Long id){
        try{
            return new ResponseEntity(hotelService.updateHotelById(hotel, id), HttpStatus.OK);
        }catch(HotelNotfoundException e){
            log.error(e.getMessage());
            return new ResponseEntity(new ErrorResponse(e.getMessage()),HttpStatus.NOT_FOUND);
        }
    }

}
