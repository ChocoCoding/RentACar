package org.example.repository;

import java.sql.*;

public interface Conexion {

    Connection getConnection() throws SQLException;
    void close(ResultSet rs) throws SQLException;
    void close(Statement rs) throws SQLException;
    void close(PreparedStatement smtm) throws SQLException;
    void close(Connection conn) throws SQLException;

}
