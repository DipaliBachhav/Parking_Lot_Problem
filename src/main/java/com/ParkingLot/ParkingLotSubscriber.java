package com.ParkingLot;

public interface ParkingLotSubscriber {
    boolean isParkingFull();
    void parkingTime(int parkingTime);

    int getParkingTime();
    void parkingFull(boolean parkingCapacity);
}
