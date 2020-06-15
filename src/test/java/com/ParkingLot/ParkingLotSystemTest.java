package com.ParkingLot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotSystemTest {
    private Object vehicle;
    private ParkingLotSystem parkingLotSystem;

        @Before
        public void setUp() {
            parkingLotSystem = new ParkingLotSystem();
            vehicle = new Object();
        }

        @Test
        public void givenVehicle_WhenParked_ShouldReturnTrue() {
            ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
            parkingLotSystem.parkVehicle(vehicle);
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
            Assert.assertTrue(isParked);
        }

    @Test
    public void givenVehicle_WhenNotParked_ShouldReturnFalse() {
        try {
            parkingLotSystem.isVehicleParked(vehicle);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals("Vehicle Is Not Available", e.getMessage());
        }
    }
}
