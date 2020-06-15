package com.ParkingLot;

public class ParkingOwner implements ParkingLotSubscriber  {
    private boolean parkingCapacity;

    @Override
    public boolean isParkingFull() {
        return this.parkingCapacity;
    }

    @Override
    public void parkingFull(boolean parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
    }

}
