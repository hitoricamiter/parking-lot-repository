package ru.zaikin.parking_pot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zaikin.parking_pot.entity.Car;
import ru.zaikin.parking_pot.entity.ParkingSession;
import ru.zaikin.parking_pot.repository.CarRepository;

import java.time.LocalDateTime;

@Service
@Transactional
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository parkingRepository) {
        this.carRepository = parkingRepository;
    }

    public void save(Car car) {



        if (carRepository.existsByNumber(car.getNumber())) {
            Car existingCar = carRepository.findByNumber(car.getNumber());
            ParkingSession parkingSession = new ParkingSession();
            parkingSession.setEntry(LocalDateTime.now());
            existingCar.addParkingSession(parkingSession);
            carRepository.save(existingCar);
        } else {
            ParkingSession parkingSession = new ParkingSession();
            parkingSession.setEntry(LocalDateTime.now());
            car.addParkingSession(parkingSession);
            carRepository.save(car);
        }
    }

}
