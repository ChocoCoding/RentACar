package org.example.model;

/**
 * @Author: Gonzalo Campos Dominguez
 * @Date: 12/04/2023
 */
public class Car {
    Long idCar;
    String licensePlate;

    public Car(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Car(Long idCar, String licensePlate) {
        this.idCar = idCar;
        this.licensePlate = licensePlate;
    }
}
