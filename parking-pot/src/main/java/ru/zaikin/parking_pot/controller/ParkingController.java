package ru.zaikin.parking_pot.controller;

import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
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

    @PostMapping("/exit")
    public ResponseEntity<LocalDateTime> exitCar(@RequestParam String number) {
        parkingService.setExit(number);

        return ResponseEntity.ok(LocalDateTime.now());
    }



    // http://localhost:8080/v1/parking/report?start_date=2025-06-14T06:26:16.786881&end_date=2025-06-14T11:58:54.375699
    //              year-month-dayTHours:minutes:seconds | Time Zone American

    @GetMapping("/report")
    public ResponseEntity<String> getReport(
            @RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start_data,
            @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end_data
            ) {
        return ResponseEntity.ok(parkingService.getReport(start_data, end_data));
    }

















   /* @ExceptionHandler(CarAlreadyParkedException.class)
    public ResponseEntity<String> handleCarAlreadyParked(CarAlreadyParkedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }*/



}
