package ru.zaikin.parking_pot.service;

import org.springframework.stereotype.Service;
import ru.zaikin.parking_pot.entity.Car;
import ru.zaikin.parking_pot.repository.ParkingRepository;

@Service
public class CarService {
    private final ParkingRepository parkingRepository;

    public CarService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

}
