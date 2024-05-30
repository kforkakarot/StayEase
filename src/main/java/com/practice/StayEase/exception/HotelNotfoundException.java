package com.practice.StayEase.exception;

public class HotelNotfoundException extends RuntimeException{

    public HotelNotfoundException(){
        super();
    }

    public HotelNotfoundException(String msg){
        super(msg);
    }

}
