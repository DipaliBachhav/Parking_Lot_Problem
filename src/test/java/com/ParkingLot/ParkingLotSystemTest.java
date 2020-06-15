package com.ParkingLot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystemTest {
    private Object vehicle;
    private ParkingLotSystem parkingLotSystem;
    private List<Integer> listOfEmptyParkingSlots;

    @Before
        public void setUp() {
        parkingLotSystem = new ParkingLotSystem(1);
        listOfEmptyParkingSlots = parkingLotSystem.getListOfEmptyParkingSlots();
        vehicle = new Object();
        }

        @Test
        public void givenVehicleToPark_WhenParked_ShouldReturnTrue() {
            parkingLotSystem.parkVehicle(vehicle, listOfEmptyParkingSlots.get(0));
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
            Assert.assertTrue(isParked);
        }

    @Test
    public void givenVehicleToPark_WhenNotParked_ShouldReturnException() {
        try {
            parkingLotSystem.parkVehicle(vehicle, listOfEmptyParkingSlots.get(0));
            parkingLotSystem.isVehicleParked(new Object());
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals("Vehicle Is Not Available", e.getMessage());
        }
    }

    @Test
    public void givenVehicleToPark_WhenCapacityIs2_ShouldBeAbleToPark2Vehicle() {
        parkingLotSystem.setCapacity(2);
        Object vehicle1 = new Object();
        parkingLotSystem.parkVehicle(vehicle, listOfEmptyParkingSlots.get(0));
        boolean isParked1 = parkingLotSystem.isVehicleParked(vehicle);
        parkingLotSystem.parkVehicle(vehicle1, listOfEmptyParkingSlots.get(0));
        boolean isParked2 = parkingLotSystem.isVehicleParked(vehicle1);
        Assert.assertTrue(isParked1 && isParked2);
    }

    @Test
    public void givenVehicleToPark_WhenSameVehicleAlreadyParked_ShouldThrowException() {
        parkingLotSystem.setCapacity(2);
        try {
            parkingLotSystem.parkVehicle(vehicle, listOfEmptyParkingSlots.get(0));
            parkingLotSystem.parkVehicle(vehicle, listOfEmptyParkingSlots.get(0));
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals("Vehicle Already Parked", e.getMessage());
        }
    }

    @Test
    public void givenVehicleToUnpark_WhenParked_ShouldReturnTrue() {
        parkingLotSystem.parkVehicle(vehicle, listOfEmptyParkingSlots.get(0));
        boolean unparkVehicle = parkingLotSystem.unparkVehicle(vehicle);
        Assert.assertTrue(unparkVehicle);
    }

    @Test
    public void givenVehicleToUnpark_WhenNotParked_ShouldReturnException() {
        parkingLotSystem.parkVehicle(vehicle, listOfEmptyParkingSlots.get(0));
        try {
            parkingLotSystem.unparkVehicle(new Object());
        } catch(ParkingLotSystemException e) {
            Assert.assertEquals("Vehicle Is Not Available", e.getMessage());
        }
    }

    @Test
    public void givenVehicle_WhenParkingFullAndOwnerIsObserver_ShouldInformOwner() {
        ParkingOwner parkingOwner = new ParkingOwner();
        try {
            parkingLotSystem.subscribe(parkingOwner);
            parkingLotSystem.parkVehicle(new Object(), listOfEmptyParkingSlots.get(0));
            Assert.assertTrue(parkingOwner.isParkingFull());
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals("Parking Is Full", e.getMessage());
        }
    }

    @Test
    public void givenVehicle_WhenParkingFullAndAirportSecurityIsObserver_ShouldInformAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        try {
            parkingLotSystem.subscribe(airportSecurity);
            parkingLotSystem.parkVehicle(vehicle, listOfEmptyParkingSlots.get(0));
            parkingLotSystem.parkVehicle(new Object(), listOfEmptyParkingSlots.get(0));
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals("Parking Is Full", e.getMessage());
            Assert.assertTrue(airportSecurity.isParkingFull());
        }
    }

    @Test
    public void givenVehicle_WhenParkingFullAndAirportSecurityIsNotObserver_ShouldInformAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        try {
            parkingLotSystem.subscribe(airportSecurity);
            parkingLotSystem.unsubscribe(airportSecurity);
            parkingLotSystem.parkVehicle(vehicle, listOfEmptyParkingSlots.get(0));
            parkingLotSystem.parkVehicle(new Object(), listOfEmptyParkingSlots.get(0));
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals("Parking Is Full", e.getMessage());
            Assert.assertFalse(airportSecurity.isParkingFull());
        }
    }

    @Test
    public void givenVehicle_WhenParkingFullAndParkingOwnerAndAirportSecurityAreObserver_ShouldInformBothParkingOwnerAndAirportSecurity() {
        ParkingOwner parkingOwner = new ParkingOwner();
        AirportSecurity airportSecurity = new AirportSecurity();
        try {
            parkingLotSystem.subscribe(parkingOwner);
            parkingLotSystem.subscribe(airportSecurity);
            parkingLotSystem.parkVehicle(vehicle, listOfEmptyParkingSlots.get(0));
            Assert.assertTrue(parkingOwner.isParkingFull());
            Assert.assertTrue(airportSecurity.isParkingFull());
            parkingLotSystem.parkVehicle(new Object(), listOfEmptyParkingSlots.get(0));
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals("Parking Is Full", e.getMessage());
            Assert.assertTrue(parkingOwner.isParkingFull());
            Assert.assertTrue(airportSecurity.isParkingFull());
        }
    }

    @Test
    public void givenVehicle_WhenParkingavailableAndOwnerIsObserver_ShouldInformOwner() {
        ParkingOwner parkingOwner = new ParkingOwner();
        try {
            parkingLotSystem.subscribe(parkingOwner);
            parkingLotSystem.parkVehicle(vehicle, listOfEmptyParkingSlots.get(0));
            parkingLotSystem.parkVehicle(new Object(), listOfEmptyParkingSlots.get(0));
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals("Parking Is Full", e.getMessage());
            Assert.assertTrue(parkingOwner.isParkingFull());
        }
        parkingLotSystem.unparkVehicle(vehicle);
        Assert.assertFalse(parkingOwner.isParkingFull());
    }

    //UC6
    @Test
    public void givenParkingLotSystem_WhenParkingCapacityIsSet_ShouldReturnParkingCapacity() {
        int parkingLotCapacity = parkingLotSystem.setCapacity(100);
        Assert.assertEquals(100, parkingLotCapacity);
    }

    @Test
    public void givenParkingLotSystem_WhenListOfEmptySlotsCalled_ShouldReturnAvailableSlots() {
        List<Integer> expectedList = new ArrayList();
        expectedList.add(1);
        parkingLotSystem.setCapacity(2);
        listOfEmptyParkingSlots = parkingLotSystem.getListOfEmptyParkingSlots();
        parkingLotSystem.parkVehicle(vehicle, listOfEmptyParkingSlots.get(0));
        listOfEmptyParkingSlots = parkingLotSystem.getListOfEmptyParkingSlots();
        Assert.assertEquals(expectedList, listOfEmptyParkingSlots);
    }


    //UC7
    @Test
    public void givenParkingLotSystem_WhenVehicleFound_ShouldReturnVehicleSlot() {
        parkingLotSystem.setCapacity(10);
        listOfEmptyParkingSlots = parkingLotSystem.getListOfEmptyParkingSlots();
        parkingLotSystem.parkVehicle(vehicle, listOfEmptyParkingSlots.get(0));
        int slotNumber = parkingLotSystem.findVehicle(vehicle);
        Assert.assertEquals(0, slotNumber);
    }

    @Test
    public void givenParkingLotSystem_WhenVehicleNotFound_ShouldReturnException() {
        parkingLotSystem.setCapacity(10);
        listOfEmptyParkingSlots = parkingLotSystem.getListOfEmptyParkingSlots();
        try {
            parkingLotSystem.findVehicle(vehicle);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals("Vehicle Is Not Available", e.getMessage());
        }
    }

}
