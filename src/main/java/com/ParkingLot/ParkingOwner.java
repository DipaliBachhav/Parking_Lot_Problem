package com.ParkingLot;

public class ParkingOwner {
    private boolean parkingCapacity;

    public void parkingFull() {
        this.parkingCapacity = true;
    }

    public boolean isParkingFull() {
        return this.parkingCapacity;
    }
}

