package ru.zaikin.parking_pot.integration_tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.zaikin.parking_pot.entity.Car;
import ru.zaikin.parking_pot.service.CarService;
import ru.zaikin.parking_pot.utility.CarType;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers
@SpringBootTest
public class CarServiceTestContainerTests {

    @Autowired
    private CarService carService;

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:15")
                    .withDatabaseName("testdb")
                    .withUsername("user")
                    .withPassword("password");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    }

    @Test
    void saveCarSuccessfully() {
        Car car = new Car();
        car.setNumber("93NZRU");
        car.setCarType(CarType.CARGO);
        carService.save(car);

        assertNotNull(car.getEntryParkingTime(), "Время должно быть указано");
    }

    /*@Test
    void saveCarThrowsExceptionBecauseOfNumberAlreadyExist() {
        Car car = new Car();
        car.setNumber("93NZRU");
        car.setCarType(CarType.CARGO);
        carService.save(car);

        Car dubplicateCar = new Car();
        dubplicateCar.setNumber("93NZRU");
        dubplicateCar.setCarType(CarType.CARGO);


        assertThrows(CarAlreadyParkedException.class, () -> carService.save(dubplicateCar));


    }*/


}
