package com.practice.StayEase.service.Impl;

import com.practice.StayEase.entity.Hotel;
import com.practice.StayEase.exception.HotelNotfoundException;
import com.practice.StayEase.repository.HotelRepository;
import com.practice.StayEase.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public Iterable<Hotel> getAllHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel findHotelById(Long id) throws HotelNotfoundException{
        if(hotelRepository.findById(id).isPresent()){
            return hotelRepository.findById(id).get();
        }
        throw new HotelNotfoundException("Hotel Not found");
    }

    @Override
    public Hotel addNewHotel(Hotel hotel){
        return hotelRepository.save(hotel);
    }

    @Override
    public void deleteHotelById(Long id) throws HotelNotfoundException {
        if(hotelRepository.findById(id).isPresent()){
            hotelRepository.deleteById(id);
        }
        else throw new HotelNotfoundException("Hotel Not found");
    }

    @Override
    public Hotel updateHotelById(Hotel hotel, Long id) throws HotelNotfoundException{
        if(hotelRepository.findById(id).isPresent()){
            Hotel hotelToUpdate = hotelRepository.findById(id).get();

            hotelToUpdate.setHotelName(hotel.getHotelName());
            hotelToUpdate.setLocation(hotel.getLocation());
            hotelToUpdate.setDescription(hotel.getDescription());
            hotelToUpdate.setNumberOfAvailableRooms(hotel.getNumberOfAvailableRooms());
            return hotelRepository.save(hotelToUpdate);
        }
        throw new HotelNotfoundException("Hotel Not found");
    }
}
