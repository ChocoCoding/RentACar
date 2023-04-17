package org.example.repository;

import org.example.model.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Gonzalo Campos Dominguez
 * @Date: 12/04/2023
 */
class CarRepositoryTest {
    CarRepository repository;

    @BeforeEach
    void setUp() {
        Car car1 = new Car("ABBA");
        Car car2 = new Car("CAAC");
        repository.add(car1);
        repository.add(car2);
    }

    @Test
    void add() {
        Car car3 = new Car("XDXD");

    }

    @Test
    void deleteById() {

    }

    @Test
    void findAll() {
    }

    @Test
    void nextIdAvailable() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByLicensePlate() {
    }

    @Test
    void update() {
    }
}