package ru.zaikin.parking_pot.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ParkingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime entry;
    private LocalDateTime exit;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public ParkingSession(LocalDateTime entry, LocalDateTime exit) {
        this.entry = entry;
        this.exit = exit;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ParkingSession() {
    }

    public LocalDateTime getEntry() {
        return entry;
    }

    public void setEntry(LocalDateTime entry) {
        this.entry = entry;
    }

    public LocalDateTime getExit() {
        return exit;
    }

    public void setExit(LocalDateTime exit) {
        this.exit = exit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
