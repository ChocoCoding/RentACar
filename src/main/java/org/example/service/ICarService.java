package org.example.service;

import org.example.model.Car;
import org.example.model.Client;

import java.util.ArrayList;

public interface ICarService {

    void add(Car car);
    void deleteById(Long id);
    ArrayList findAll();
    Car findByLicensePlate(String LicensePlate);
    void update(Car car);
}
