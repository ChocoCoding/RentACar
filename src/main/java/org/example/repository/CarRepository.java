package org.example.repository;

import org.example.model.Car;

import java.sql.*;
import java.util.ArrayList;


public class CarRepository implements ICarRepository {

    String JDBC_URL = "jdbc:mysql://localhost:3306/rentacar?useSSL=false&useTimezone&useTimezone=true&serverTimezone=UTC" +
            "&allowPublicKeyRetrieval=true";
    String JDBC_USER = "root";
    String JDBC_PASSWORD = "admin";

    private static final String SQL_SELECT = "SELECT id_car,license_plate FROM rentacar.car;";
    private static final String SQL_SELECT_BY_ID = "SELECT id_car,license_plate FROM rentacar.car WHERE id_car = ?;";
    private static final String SQL_SELECT_BY_LICENSE_PLATE = "SELECT id_car,license_plate FROM rentacar.car WHERE license_plate = ?;";
    private static final String SQL_INSERT = "INSERT INTO rentacar.car(license_plate) VALUE(?);";
    private static final String SQL_UPDATE = "UPDATE rentacar.car SET license_plate= ? WHERE id_car = ?;";
    private static final String SQL_DELETE = "DELETE FROM rentacar.car WHERE id_car = ?;";


    Connection connection = null;

    public Connection getConnection() throws SQLException {
        if (connection == null)
            return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        else return connection;
    }


    //Interface IcarRepository

    //Add car method
    @Override
    public void add(Car car) {
        if (!licenseExists(car.getLicensePlate())) {
            Connection conn = null;
            PreparedStatement stmt = null;
            int registros = 0;
            try {
                conn = getConnection();
                stmt = conn.prepareStatement(SQL_INSERT);
                stmt.setString(1, car.getLicensePlate());
                registros = stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            } finally {
                if (stmt != null) {
                try {
                        stmt.close();
                        conn.close();
                } catch (SQLException e) { /* Ignored */ }

            }
        }
        } else System.out.println("La Matricula: " + car.getLicensePlate() + " ya esta registrada");
    }

    //DeleteByIdMethod (Completed) TODO Se puede eliminar a traves del findAll
    @Override
    public void deleteById(Long id) {
        if (!isEmpty()) {
            if ((findById(id) != null)) {
                Connection conn = null;
                PreparedStatement stmt = null;
                int registros = 0;

                try {
                    conn = getConnection();
                    stmt = conn.prepareStatement(SQL_DELETE);
                    stmt.setInt(1, Math.toIntExact(id));
                    registros = stmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace(System.out);
                } finally {
                    try {
                        stmt.close();
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace(System.out);
                    }
                }
                System.out.println("La matricula con id: " + id + " se ha eliminado correctamente");
            } else System.out.println("La id: " + id + " no existe");

        }

    }

    //List all database content method (Completed)
    @Override
    public ArrayList<Car> findAll() {
        ArrayList<Car> carList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Long idCar = (long) rs.getInt("id_car");
                String licensePlate = rs.getString("license_plate");
                Car car = new Car(idCar, licensePlate);
                carList.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return carList;
    }

    //Find by id method
    @Override
    public Car findById(Long id) {
        if (!isEmpty()) {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Car car = null;
            int registros = 0;
            try {
                conn = getConnection();
                stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
                stmt.setLong(1, id);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    Long idCar = rs.getLong("id_car");
                    String licensePlate = rs.getString("license_plate");
                    car = new Car(idCar, licensePlate);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                if (stmt != null && rs != null) {
                    try {
                        rs.close();
                        stmt.close();
                        conn.close();
                    } catch (SQLException e) { /* Ignored */ }

                }
            }
            return car;
        } else return null;

    }

    //Find by license plate method (Completed)
    @Override
    public Car findByLicensePlate(String LicensePlate) { //TODO se podria hacer recorriendo el array de findAll
        if (licenseExists(LicensePlate)) {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Car car = null;
            int registros = 0;
            try {
                conn = getConnection();
                stmt = conn.prepareStatement(SQL_SELECT_BY_LICENSE_PLATE);
                stmt.setString(1, LicensePlate);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    Long idCar = (long) rs.getInt("id_car");
                    String licensePlate = rs.getString("license_plate");
                    car = new Car(idCar, licensePlate);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                if (stmt != null && rs != null) {
                    try {
                        rs.close();
                        stmt.close();
                        conn.close();
                    } catch (SQLException e) { /* Ignored */ }

                }
            }
            return car;
        }
        return null;
    }

    //Update field throught an object
    @Override
    public void update(Car car) {
        if (!isEmpty()) {
            Connection conn = null;
            PreparedStatement stmt = null;
            int registros = 0;
            try {
                conn = getConnection();
                stmt = conn.prepareStatement(SQL_UPDATE);
                stmt.setString(1, car.getLicensePlate());
                stmt.setLong(2, car.getIdCar());
                registros = stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            } if (stmt != null) {
                try {
                    stmt.close();
                    conn.close();
                } catch (SQLException e) { /* Ignored */ }

            }
        }
    }

    //Method is empty: Check if the database is empty
    public boolean isEmpty() {
        if (findAll().size() == 0) {
            return true;
        } else return false;
    }

    //Method license exist: Check if a license plate exists in Database
    public boolean licenseExists(String licensePlate) {
        if (!isEmpty()) {
            for (Car car : findAll()) {
                if (car.getLicensePlate().equalsIgnoreCase(licensePlate)) {
                    return true;
                }
            }
        } else return false;
        return false;
    }


}
