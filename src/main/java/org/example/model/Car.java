package org.example.model;

public class Car {

    Long idCar;
    String licensePlate;

    public Car(Long idCar){
        this.idCar = idCar;
    }

    public Car(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Car(Long idCar, String licensePlate) {
        this.idCar = idCar;
        this.licensePlate = licensePlate;
    }



    public Long getIdCar() {
        return idCar;
    }

    public void setIdCar(Long idCar) {
        this.idCar = idCar;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(idCar).append("\t | ");
        sb.append("License Plate Number: ").append(licensePlate).append("\n");
        return sb.toString();
    }
}
