package ru.zaikin.parking_pot.service;

import org.springframework.stereotype.Service;
import ru.zaikin.parking_pot.entity.Car;
import ru.zaikin.parking_pot.entity.ParkingSession;
import ru.zaikin.parking_pot.repository.CarRepository;
import ru.zaikin.parking_pot.repository.ParkingRepository;

import java.time.LocalDateTime;

@Service
public class ParkingService {
    private final ParkingRepository parkingRepository;
    private final CarRepository carRepository;

    public ParkingService(ParkingRepository parkingRepository, CarRepository carRepository) {
        this.parkingRepository = parkingRepository;
        this.carRepository = carRepository;
    }

    public void setExit(String number) {
        Car car = carRepository.findByNumber(number);
        ParkingSession session = parkingRepository.findTopByCarAndExitIsNullOrderByEntryDesc(car).get();
        session.setExit(LocalDateTime.now());
        parkingRepository.save(session);

    }

}
