package ru.zaikin.parking_pot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zaikin.parking_pot.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    boolean existsByNumber(String number);
    Car findByNumber(String number);

}
