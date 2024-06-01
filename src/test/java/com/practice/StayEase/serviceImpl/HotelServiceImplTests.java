package com.practice.StayEase.serviceImpl;

import com.practice.StayEase.entity.Hotel;
import com.practice.StayEase.exception.HotelNotfoundException;
import com.practice.StayEase.repository.HotelRepository;
import com.practice.StayEase.service.Impl.HotelServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HotelServiceImplTests {

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelServiceImpl hotelServiceImpl;

    private Hotel hotel;

    @BeforeEach
    public void setUp() {
        hotel = new Hotel();
        hotel.setId(1L);
        hotel.setHotelName("Test Hotel");
        hotel.setLocation("Test Location");
        hotel.setDescription("Test Description");
        hotel.setNumberOfAvailableRooms(10L);
    }

    @Test
    public void testGetAllHotel() {
        when(hotelRepository.findAll()).thenReturn(List.of(hotel));

        Iterable<Hotel> hotels = hotelServiceImpl.getAllHotel();

        assertNotNull(hotels);
        assertEquals(hotel, hotels.iterator().next());
    }

    @Test
    public void testFindHotelById_Success() throws HotelNotfoundException {
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel));

        Hotel foundHotel = hotelServiceImpl.findHotelById(1L);

        assertNotNull(foundHotel);
        assertEquals(hotel, foundHotel);
    }

    @Test
    public void testFindHotelById_NotFound() {
        when(hotelRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(HotelNotfoundException.class, () -> {
            hotelServiceImpl.findHotelById(1L);
        });
    }

    @Test
    public void testAddNewHotel() {
        when(hotelRepository.save(hotel)).thenReturn(hotel);

        Hotel savedHotel = hotelServiceImpl.addNewHotel(hotel);

        assertNotNull(savedHotel);
        assertEquals(hotel, savedHotel);
    }


    @Test
    public void testDeleteHotelById_NotFound() {
        when(hotelRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(HotelNotfoundException.class, () -> {
            hotelServiceImpl.deleteHotelById(1L);
        });
    }

    @Test
    public void testUpdateHotelById_Success() throws HotelNotfoundException {
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel));
        when(hotelRepository.save(any(Hotel.class))).thenReturn(hotel);

        Hotel updatedHotel = hotelServiceImpl.updateHotelById(hotel, 1L);

        assertNotNull(updatedHotel);
        assertEquals(hotel, updatedHotel);
    }

    @Test
    public void testUpdateHotelById_NotFound() {
        when(hotelRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(HotelNotfoundException.class, () -> {
            hotelServiceImpl.updateHotelById(hotel, 1L);
        });
    }
}
