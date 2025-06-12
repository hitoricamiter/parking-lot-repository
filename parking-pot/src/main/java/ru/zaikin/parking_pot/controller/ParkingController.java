package ru.zaikin.parking_pot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zaikin.parking_pot.entity.Car;

@RestController
@RequestMapping("/v1/parking")
public class ParkingController {


    @PostMapping("/entry")
    public ResponseEntity<String> receiveCar(@RequestBody Car car) {
        System.out.println(car.toString());

        return ResponseEntity.ok("received");
    }



}
