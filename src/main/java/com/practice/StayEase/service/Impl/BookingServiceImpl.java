package com.practice.StayEase.service.Impl;

import com.practice.StayEase.dto.Email;
import com.practice.StayEase.entity.Booking;
import com.practice.StayEase.entity.Hotel;
import com.practice.StayEase.exception.BookingNotFoundException;
import com.practice.StayEase.exception.HotelNotfoundException;
import com.practice.StayEase.exception.RoomNotAvailableException;
import com.practice.StayEase.repository.BookingRepository;
import com.practice.StayEase.repository.HotelRepository;
import com.practice.StayEase.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final HotelRepository hotelRepository;

    @Override
    public Booking makeBooking(Long hotelId, Email guestEmail) throws HotelNotfoundException, RoomNotAvailableException {
        if(hotelRepository.findById(hotelId).isPresent()){
            if(bookingRepository.findByEmailAndHotelId(hotelId, guestEmail.getEmail()) != null){
                Hotel hotelToUpdate = hotelRepository.findById(hotelId).get();
                if(hotelToUpdate.getNumberOfAvailableRooms() > 0){
                    hotelToUpdate.setNumberOfAvailableRooms(hotelToUpdate.getNumberOfAvailableRooms() - 1);
                    hotelRepository.save(hotelToUpdate);
                    Booking updateBooking = bookingRepository.findByEmailAndHotelId(hotelId, guestEmail.getEmail());
                    updateBooking.setNumberOfRooms(updateBooking.getNumberOfRooms() + 1);
                    return bookingRepository.save(updateBooking);
                }
                else throw new RoomNotAvailableException("Room full in current Hotel");

            }
            else{
                Hotel hotelToUpdate = hotelRepository.findById(hotelId).get();
                if(hotelToUpdate.getNumberOfAvailableRooms() > 0){
                    hotelToUpdate.setNumberOfAvailableRooms(hotelToUpdate.getNumberOfAvailableRooms() - 1);
                    hotelRepository.save(hotelToUpdate);
                    Booking newBooking = new Booking();
                    newBooking.setNumberOfRooms(1L);
                    newBooking.setHotelId(hotelId);
                    newBooking.setGuestEmail(guestEmail.getEmail());
                    return bookingRepository.save(newBooking);
                }

            }
        }
        throw new HotelNotfoundException("Hotel ID not found");
    }

    @Override
    public void deleteBooking(Long bookingId) throws BookingNotFoundException {
        if(bookingRepository.findById(bookingId).isPresent()){
            Booking bookingToDelete = bookingRepository.findById(bookingId).get();
            Hotel hotelToUpdate = hotelRepository.findById(bookingToDelete.getHotelId()).get();
            hotelToUpdate.setNumberOfAvailableRooms(bookingToDelete.getNumberOfRooms());
            hotelRepository.save(hotelToUpdate);
            bookingRepository.deleteById(bookingId);
        }
        else throw  new BookingNotFoundException("Booking does not exist");
    }
}
