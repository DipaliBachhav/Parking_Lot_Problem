package com.ParkingLot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ParkingLotSystem {
    private  int parkingCapacity;
    private List vehiclesList;
    private ParkingOwner parkingOwner;
    InformObserver informObserver;


    public ParkingLotSystem(int parkingCapacity) {
        setCapacity(parkingCapacity);
        this.informObserver = new InformObserver();
    }

    public int setCapacity(int parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
        initializeParkingLot();
        return vehiclesList.size();
    }

    public void initializeParkingLot() {
        this.vehiclesList = new ArrayList();
        IntStream.range(0, this.parkingCapacity)
                .forEach(slots -> vehiclesList.add(null));
    }

    public void parkVehicle(Object vehicle, Integer slot) {
        if (this.parkingCapacity == this.vehiclesList.size() && !vehiclesList.contains(null)) {
            throw new ParkingLotSystemException("Parking Is Full", ParkingLotSystemException.ExceptionType.PARKING_FULL);
        }
        if (vehiclesList.contains(vehicle)) {
            throw new ParkingLotSystemException("Vehicle Already Parked", ParkingLotSystemException.ExceptionType.VEHICLE_ALREADY_PARKED);
        }
        vehiclesList.set(slot, vehicle);
        if (this.parkingCapacity == this.vehiclesList.size() && !vehiclesList.contains(null)) {
            informObserver.parkingFull();
        }
    }

    public void subscribe(ParkingLotSubscriber subscriber) {
        informObserver.subscribeParkingLotObserver(subscriber);
    }

    public boolean isVehicleParked(Object vehicle) {
        if (vehiclesList.contains(vehicle))
            return true;
        throw new ParkingLotSystemException("Vehicle Is Not Available", ParkingLotSystemException.ExceptionType.VEHICLE_NOT_FOUND);
    }

    public boolean unparkVehicle(Object vehicle) {
        if (this.vehiclesList.contains(vehicle)) {
            vehiclesList.remove(vehicle);
            informObserver.parkingAvailable();
            return true;
        }
            throw new ParkingLotSystemException("Vehicle Is Not Available", ParkingLotSystemException.ExceptionType.VEHICLE_NOT_FOUND);
    }

    public List<Integer> getListOfEmptyParkingSlots() {
        List<Integer> emptyParkingSlotList = new ArrayList<>();
        IntStream.range(0, this.parkingCapacity)
                .filter(slot -> vehiclesList.get(slot) == null)
                .forEach(emptyParkingSlotList::add);
        return emptyParkingSlotList;
    }

    public void unsubscribe(ParkingLotSubscriber subscriber) {
        informObserver.unsubscribeParkingLotObserver(subscriber);
    }

    public int findVehicle(Object vehicle) {
        if(vehiclesList.contains(vehicle)) {
            return vehiclesList.indexOf(vehicle);
        }
        throw new ParkingLotSystemException("Vehicle Is Not Available", ParkingLotSystemException.ExceptionType.VEHICLE_NOT_FOUND);
    }
}
