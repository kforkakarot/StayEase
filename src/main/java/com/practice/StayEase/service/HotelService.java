package com.practice.StayEase.service;

import com.practice.StayEase.entity.Hotel;
import com.practice.StayEase.exception.HotelNotfoundException;


public interface HotelService {

    Iterable<Hotel> getAllHotel();
    Hotel findHotelById(Long id) throws HotelNotfoundException;
    Hotel addNewHotel(Hotel hotel);
    void deleteHotelById(Long id)throws HotelNotfoundException;
    Hotel updateHotelById(Hotel hotel, Long id)throws HotelNotfoundException;

}
