package ru.zaikin.parking_pot.entity;

import jakarta.persistence.*;
import ru.zaikin.parking_pot.utility.CarType;

import java.time.LocalDateTime;


@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String number;
    private LocalDateTime parkingTime;
    @Enumerated(EnumType.STRING)
    private CarType carType;

    public Car(Long id, String number, LocalDateTime parkingTime, CarType carType) {
        this.id = id;
        this.number = number;
        this.parkingTime = parkingTime;
        this.carType = carType;
    }

    public Car(String number, CarType carType) {
        this.number = number;
        this.carType = carType;
    }

    public Car() {

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(LocalDateTime parkingTime) {
        this.parkingTime = parkingTime;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", parkingTime=" + parkingTime +
                '}';
    }
}
