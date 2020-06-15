package com.ParkingLot;

public interface ParkingLotSubscriber {
    boolean isParkingFull();

    void parkingFull(boolean parkingCapacity);
}
