package ru.zaikin.parking_pot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zaikin.parking_pot.entity.Car;
import ru.zaikin.parking_pot.service.CarService;
import ru.zaikin.parking_pot.utility.CarAlreadyParkedException;

@RestController
@RequestMapping("/v1/parking")
public class ParkingController {

    private final CarService carService;

    public ParkingController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/entry")
    public ResponseEntity<String> receiveCar(@RequestBody Car car) {
        carService.save(car);

        return ResponseEntity.ok("received");
    }

    @ExceptionHandler(CarAlreadyParkedException.class)
    public ResponseEntity<String> handleCarAlreadyParked(CarAlreadyParkedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }



}
