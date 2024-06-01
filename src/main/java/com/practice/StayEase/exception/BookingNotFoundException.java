package com.practice.StayEase.exception;

public class BookingNotFoundException extends RuntimeException{

    public BookingNotFoundException(){
        super();
    }

    public BookingNotFoundException(String msg){
        super(msg);
    }
}
