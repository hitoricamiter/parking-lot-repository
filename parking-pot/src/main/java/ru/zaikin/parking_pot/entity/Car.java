package ru.zaikin.parking_pot.entity;

import jakarta.persistence.*;
import ru.zaikin.parking_pot.utility.CarType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String number;
    @Enumerated(EnumType.STRING)
    private CarType carType;
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParkingSession> parkingSessionList = new ArrayList<>();

    public Car(Long id, String number, CarType carType) {
        this.id = id;
        this.number = number;
        this.carType = carType;
    }

    public Car(String number, CarType carType) {
        this.number = number;
        this.carType = carType;
    }

    public Car() {

    }

    public void addParkingSession(ParkingSession parkingSession) {
        parkingSession.setCar(this);
        parkingSessionList.add(parkingSession);
    }

    public String getNumber() {
        return number;
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

    public void setNumber(String number) {
        this.number = number;
    }

    public List<ParkingSession> getParkingSessionList() {
        return parkingSessionList;
    }

    public void setParkingSessionList(List<ParkingSession> parkingSessionList) {
        this.parkingSessionList = parkingSessionList;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
