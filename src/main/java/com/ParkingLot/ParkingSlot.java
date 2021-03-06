package com.ParkingLot;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ParkingSlot {
    public int time;
    public Object vehicle;
    private Integer slot;

    public ParkingSlot(Vehicle vehicle, int slot) {
        this.vehicle = vehicle;
        this.time = (int) TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis());
        this.slot = slot;
    }

    public int getLocation() {
        return this.slot;
    }

    public Object getVehicle() {
        return this.vehicle;
    }


    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlot that = (ParkingSlot) o;
        return Objects.equals(vehicle, that.vehicle);
    }
}
