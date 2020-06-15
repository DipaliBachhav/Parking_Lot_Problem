package com.ParkingLot;

public class ParkingLotSystemException extends RuntimeException{
    public enum ExceptionType {
        VEHICLE_NOT_FOUND,PARKING_FULL,VEHICLE_ALREADY_PARKED;
    }

    private ExceptionType type;

    public ParkingLotSystemException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

}
