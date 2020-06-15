package com.ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {
    private  int parkingCapacity;
    private List vehicle;
    private ParkingOwner parkingOwner;
    private int currentCapacity = 0;
    InformObserver informObserver;


    public ParkingLotSystem(int parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
        this.vehicle = new ArrayList();
        this.informObserver = new InformObserver();
    }

    public void parkVehicle(Object vehicle) {
        if (this.parkingCapacity == currentCapacity) {
            informObserver.parkingFull();
            throw new ParkingLotSystemException("Parking Is Full", ParkingLotSystemException.ExceptionType.PARKING_FULL);
        }
        this.vehicle.add(vehicle);
        currentCapacity++;
    }

    public void subscribe(ParkingLotSubscriber subscriber) {
        informObserver.subscribeParkingLotObserver(subscriber);
    }

    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicle.contains(vehicle))
            return true;
        throw new ParkingLotSystemException("Vehicle Is Not Available", ParkingLotSystemException.ExceptionType.VEHICLE_NOT_FOUND);
    }

    public boolean unparkVehicle(Object vehicle) {
        if (this.vehicle.contains(vehicle)) {
            this.vehicle.remove(vehicle);
            informObserver.parkingAvailable();
            return true;
        }
            throw new ParkingLotSystemException("Vehicle Is Not Available", ParkingLotSystemException.ExceptionType.VEHICLE_NOT_FOUND);
    }

    public void unsubscribe(ParkingLotSubscriber subscriber) {
        informObserver.unsubscribeParkingLotObserver(subscriber);
    }

}
