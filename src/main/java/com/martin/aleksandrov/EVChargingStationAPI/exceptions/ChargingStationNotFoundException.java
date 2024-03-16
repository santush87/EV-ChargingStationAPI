package com.martin.aleksandrov.EVChargingStationAPI.exceptions;

public class ChargingStationNotFoundException extends RuntimeException{

    public ChargingStationNotFoundException(String msg) {
        super(msg);
    }
}
