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

    public String getReport(LocalDateTime from, LocalDateTime to) {
        int free = 50 - parkingRepository.countByExitIsNotNull();
        int booked = parkingRepository.countByExitIsNotNull();

        double avg = parkingRepository.findAverageDurationInSecondsBetween(from, to);

        long totalSeconds = (long) avg;

        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        // Форматируем строку с ведущими нулями
        String avgTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);



        return "Free " + free + " Booked " + booked + " AVG " + avgTime;

    }



}
