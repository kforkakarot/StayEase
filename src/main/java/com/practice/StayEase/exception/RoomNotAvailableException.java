package com.practice.StayEase.exception;

public class RoomNotAvailableException extends RuntimeException{

    public RoomNotAvailableException(){
        super();
    }

    public RoomNotAvailableException(String msg){
        super(msg);
    }
}
