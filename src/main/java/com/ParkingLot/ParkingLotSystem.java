package com.ParkingLot;

import java.util.ArrayList;

public class ParkingLotSystem {
    private  int parkingCapacity;
    private Object vehicle;
    private ParkingOwner parkingOwner;
    private int currentCapacity = 0;
    InformObserver informObserver;


    public ParkingLotSystem(int parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
        this.informObserver = new InformObserver();
    }

    public void parkVehicle(Object vehicle) {
        if (this.parkingCapacity == currentCapacity) {
            informObserver.parkingFull();
            throw new ParkingLotSystemException("Parking Is Full", ParkingLotSystemException.ExceptionType.PARKING_FULL);
        }
        this.vehicle = vehicle;
        currentCapacity++;
    }

    public void subscribe(ParkingLotSubscriber subscriber) {
        informObserver.subscribeParkingLotObserver(subscriber);
    }

    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicle.equals(vehicle))
            return true;
        throw new ParkingLotSystemException("Vehicle Is Not Available", ParkingLotSystemException.ExceptionType.VEHICLE_NOT_FOUND);
    }

    public boolean unparkVehicle(Object vehicle) {
        if (this.vehicle.equals(vehicle))
            return true;
        throw new ParkingLotSystemException("Vehicle Is Not Available", ParkingLotSystemException.ExceptionType.VEHICLE_NOT_FOUND);
    }

    public void unsubscribe(ParkingLotSubscriber subscriber) {
        informObserver.unsubscribeParkingLotObserver(subscriber);
    }

}
