package com.ParkingLot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParkingLotSystem {
    private List<ParkingLot> parkingLotList;
    private InformObserver informObserver;
    private ParkingLot parkingLot;


    public ParkingLotSystem() {
        this.informObserver = new InformObserver();
        this.parkingLotList = new ArrayList<>();
    }

    public void addLot(ParkingLot parkingLot) {
        this.parkingLotList.add(parkingLot);
    }

    public boolean isLotAdded(ParkingLot parkingLot) {
        if (this.parkingLotList.contains(parkingLot)) {
            return true;
        }
        return false;
    }

    public void parkVehicle(Vehicle vehicle, DriverType driverType, VehicleSize vehicleSize) {
        parkingLot = getParkingLotHavingMaxSpace();
        if (parkingLot.isParkingFull()) {
            throw new ParkingLotSystemException("Parking Is Full", ParkingLotSystemException.ExceptionType.PARKING_FULL);
        }
        parkingLot.parkVehicle(vehicle, driverType, vehicleSize);
        if (parkingLot.isParkingFull()) {
            informObserver.parkingFull();
        }
    }

    public void subscribe(ParkingLotSubscriber subscriber) {
        informObserver.subscribeParkingLotObserver(subscriber);
    }

    public void unsubscribe(ParkingLotSubscriber subscriber) {
        informObserver.unsubscribeParkingLotObserver(subscriber);
    }

    public boolean isVehicleParked(Vehicle vehicle) {
        for (ParkingLot parkingLot : this.parkingLotList) {
            if (parkingLot.isVehicleParked(vehicle))
                return true;
        }
        throw new ParkingLotSystemException("Vehicle Is Not Available", ParkingLotSystemException.ExceptionType.VEHICLE_NOT_FOUND);
    }

    public boolean unparkVehicle(Vehicle vehicle) {
        for (ParkingLot parkingLot : this.parkingLotList) {
            if (parkingLot.isVehicleParked(vehicle)) {
                parkingLot.unparkVehicle(vehicle);
                informObserver.parkingAvailable();
                return true;
            }
        }
        throw new ParkingLotSystemException("Vehicle Is Not Available", ParkingLotSystemException.ExceptionType.VEHICLE_NOT_FOUND);
    }

    private ParkingLot getParkingLotHavingMaxSpace() {
        return parkingLotList.stream()
                .sorted(Comparator.comparing(list -> list.getListOfEmptyParkingSlots().size(), Comparator.reverseOrder()))
                .collect(Collectors.toList()).get(0);
    }

    public int findVehicle(Vehicle vehicle) {
        for (ParkingLot parkingLot : parkingLotList)
            if (parkingLot.isVehicleParked(vehicle))
                return parkingLot.findVehicle(vehicle);
        throw new ParkingLotSystemException("Vehicle Is Not Available", ParkingLotSystemException.ExceptionType.VEHICLE_NOT_FOUND);
    }

    public void getVehicleParkingTime(Vehicle vehicle) {
        for (ParkingLot parkingLot : this.parkingLotList)
            if (parkingLot.isVehicleParked(vehicle)) {
                informObserver.setParkingTime(parkingLot.getVehicleParkingTime(vehicle));
                return;
            }
        throw new ParkingLotSystemException("Vehicle Is Not Available", ParkingLotSystemException.ExceptionType.VEHICLE_NOT_FOUND);
    }

    public List<List<Integer>> findVehicleByColor(String color) {
        List<List<Integer>> vehicleListByColor = this.parkingLotList.stream()
                .map(parkingSlot -> parkingLot.findByColor(color))
                .collect(Collectors.toList());
        return vehicleListByColor;
    }
}