package ru.zaikin.parking_pot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zaikin.parking_pot.entity.Car;
import ru.zaikin.parking_pot.service.CarService;
import ru.zaikin.parking_pot.service.ParkingService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/parking")
public class ParkingController {

    private final CarService carService;
    private final ParkingService parkingService;

    public ParkingController(CarService carService, ParkingService parkingService) {
        this.carService = carService;
        this.parkingService = parkingService;
    }

    @PostMapping("/entry")
    public ResponseEntity<LocalDateTime> receiveCar(@RequestBody Car car) {
        carService.save(car);

        return ResponseEntity.ok(LocalDateTime.now());
    }


    // POST /exit?number=XYZ123

    @PostMapping("/exit")
    public ResponseEntity<LocalDateTime> exitCar(@RequestParam String number) {
        parkingService.setExit(number);

        return ResponseEntity.ok(LocalDateTime.now());
    }

















   /* @ExceptionHandler(CarAlreadyParkedException.class)
    public ResponseEntity<String> handleCarAlreadyParked(CarAlreadyParkedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }*/



}
