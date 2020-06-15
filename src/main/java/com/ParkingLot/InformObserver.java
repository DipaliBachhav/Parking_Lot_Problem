package com.ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class InformObserver {
    public List<ParkingLotSubscriber> observersList;
    private boolean parkingCapacity;

    public InformObserver() {
        this.observersList = new ArrayList<>();
    }

    public void subscribeParkingLotObserver(ParkingLotSubscriber parkingLotSubscriber) {
        this.observersList.add(parkingLotSubscriber);
    }

    public void unsubscribeParkingLotObserver(ParkingLotSubscriber parkingLotSubscriber) {
        this.observersList.remove(parkingLotSubscriber);
    }

    public void parkingFull() {
        this.parkingCapacity = true;
        isParkingFull();
    }

    public void isParkingFull() {
        for(ParkingLotSubscriber parkingLotSubscriber : observersList)
            parkingLotSubscriber.parkingFull(this.parkingCapacity);
    }

}

