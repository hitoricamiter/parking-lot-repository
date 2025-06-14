package ru.zaikin.parking_pot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.zaikin.parking_pot.entity.Car;
import ru.zaikin.parking_pot.entity.ParkingSession;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ParkingRepository extends JpaRepository<ParkingSession, Long> {
    Optional<ParkingSession> findTopByCarAndExitIsNullOrderByEntryDesc(Car car);

    int countByExitIsNotNull();

    @Query(value = "SELECT AVG(EXTRACT(EPOCH FROM (exit - entry))) FROM parking_session " +
            "WHERE exit BETWEEN :startDate AND :endDate AND exit is not null", nativeQuery = true)
    Double findAverageDurationInSecondsBetween(@Param("startDate") LocalDateTime startDate,
                                               @Param("endDate") LocalDateTime endDate);


}
