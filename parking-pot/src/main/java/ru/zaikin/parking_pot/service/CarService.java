package ru.zaikin.parking_pot.service;

import org.springframework.stereotype.Service;
import ru.zaikin.parking_pot.entity.Car;
import ru.zaikin.parking_pot.repository.ParkingRepository;
import ru.zaikin.parking_pot.utility.CarAlreadyParkedException;

import java.time.LocalDateTime;

@Service
public class CarService {
    private final ParkingRepository parkingRepository;

    public CarService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    public void save(Car car) {

        if (parkingRepository.existsByNumber(car.getNumber())) {
            throw new CarAlreadyParkedException("Машина с таким номером уже на парковке");
        }


        car.setParkingTime(LocalDateTime.now());
        parkingRepository.save(car);
    }

}
