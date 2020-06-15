package com.ParkingLot;

public class ParkingOwner implements ParkingLotSubscriber  {
    private boolean parkingCapacity;
    private int parkingTime;

    @Override
    public boolean isParkingFull() {
        return this.parkingCapacity;
    }

    @Override
    public void parkingFull(boolean parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
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
