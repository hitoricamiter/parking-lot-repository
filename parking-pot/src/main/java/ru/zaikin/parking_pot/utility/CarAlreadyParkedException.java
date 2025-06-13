package ru.zaikin.parking_pot.utility;

public class CarAlreadyParkedException extends RuntimeException {
    public CarAlreadyParkedException(String message) {
        super(message);
    }
}
