package com.ParkingLot;

public class AirportSecurity implements ParkingLotSubscriber  {

    private boolean parkingCapacity;

    @Override
    public void parkingFull(boolean parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
    }


    @Override
    public boolean isParkingFull() {
        return this.parkingCapacity;
    }
}
