package ru.zaikin.parking_pot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zaikin.parking_pot.entity.Car;
import ru.zaikin.parking_pot.entity.ParkingSession;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ParkingRepository extends JpaRepository<ParkingSession, Long> {
    Optional<ParkingSession> findTopByCarAndExitIsNullOrderByEntryDesc(Car car);
}
