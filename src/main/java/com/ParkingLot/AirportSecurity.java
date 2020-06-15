package com.ParkingLot;

public class AirportSecurity implements ParkingLotSystemObserver {

    private boolean parkingCapacity;

    @Override
    public void parkingFull() {
        this.parkingCapacity = true;
    }

    @Override
    public boolean isParkingFull() {
        return this.parkingCapacity;
    }
}
