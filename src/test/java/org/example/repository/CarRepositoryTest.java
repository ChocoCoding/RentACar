package org.example.repository;

import org.example.model.Car;
import org.example.model.Car;
import org.example.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryTest {
    CarRepository repository;
    @BeforeEach
    void setUp() {
    repository = new CarRepository();
    Car car1 = new Car("ABBA");
    Car car2 = new Car("BCCB");
    Car car3 = new Car("CDDC");
    Car car4 = new Car("DCCD");
    repository.add(car1);
    repository.add(car2);
    repository.add(car3);
    repository.add(car4);
    }

    @Test
    void add() {
        Car car5 = new Car("EFFE");
        repository.add(car5);
        Assertions.assertEquals(5,repository.findAll().size());

    }

    @Test
    void deleteById() {
        Assertions.assertEquals(5, repository.findAll().size());
        repository.deleteById(1L); // Don't exist
        Assertions.assertEquals(4, repository.findAll().size());
        repository.deleteById(2L);
        Assertions.assertEquals(3, repository.findAll().size());
        repository.deleteById(2L);
        Assertions.assertEquals(3, repository.findAll().size());
    }

    @Test
    void findAll() {
        Assertions.assertEquals(5, repository.findAll().size());
        Car car6 = new Car("FGGF");
        repository.add(car6);
        Assertions.assertEquals(6, repository.findAll().size());
    }

    @Test
    void findById() {
        Assertions.assertEquals(8,(repository.findAll().get(repository.findAll().size()-1).getIdCar()));
    }

    @Test
    void findByLicensePlate() {
        Car car7 = new Car("GHHG");
        repository.add(car7);
        Assertions.assertEquals("GHHG",(repository.findAll().get(repository.findAll().size()-1).getLicensePlate()));
    }

    @Test
    void update() {
        Assertions.assertEquals("CDDC",(repository.findAll().get(0).getLicensePlate()));
        Car car8 = new Car(3L,"ZXXZ");
        repository.update(car8);
        Assertions.assertEquals("ZXXZ",repository.findAll().get(0).getLicensePlate());

    }
}