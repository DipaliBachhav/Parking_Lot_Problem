package com.ParkingLot;

public interface ParkingLotSubscriber {
    public boolean isParkingFull();

    void parkingFull(boolean parkingCapacity);
}
