package com.practice.StayEase.service.Impl;

import com.practice.StayEase.repository.BookingRepository;
import com.practice.StayEase.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
}
