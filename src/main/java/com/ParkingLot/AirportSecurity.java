package com.ParkingLot;

public class AirportSecurity implements ParkingLotSubscriber  {
    private int parkingTime;
    private boolean parkingCapacity;

    @Override
    public void parkingFull(boolean parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
    }

    @Override
    public boolean isParkingFull() {
        return this.parkingCapacity;
    }

    @Override
    public void parkingTime(int parkingTime) {
        this.parkingTime = parkingTime;
    }

    @Override
    public int getParkingTime() {
        return parkingTime;
    }
}
