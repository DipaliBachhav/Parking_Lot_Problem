package com.ParkingLot;

public class ParkingLotSystem {
    private Object vehicle;

    public boolean parkVehicle(Object vehicle) {
        this.vehicle = vehicle;
        return true;
    }
    public boolean isVehicleParked(Object vehicle) {
        if(this.vehicle.equals(vehicle))
            return true;
        throw new ParkingLotSystemException("Vehicle Is Not Available", ParkingLotSystemException.ExceptionType.VEHICLE_NOT_FOUND);
    }

    public boolean unparkVehicle(Object vehicle) {
        if(this.vehicle.equals(vehicle))
            return true;
        throw new ParkingLotSystemException("Vehicle Is Not Available", ParkingLotSystemException.ExceptionType.VEHICLE_NOT_FOUND);
    }
}
