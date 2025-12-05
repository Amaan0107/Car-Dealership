package org.example;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private final BasicDataSource dataSource;

    public VehicleDao(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }
    private Vehicle mapRow(ResultSet rs) throws SQLException {
        return new Vehicle(
                rs.getString("VIN"),
                rs.getInt("year"),
                rs.getString("model"),
                rs.getString("type"),
                rs.getString("color"),
                rs.getDouble("price"),
                rs.getInt("mileage")
        );
    }
    public List<Vehicle> getAllVehicles() {
        String sql = "SELECT * " +
                "FROM vehicles " +
                "ORDER BY year DESC; ";

        List<Vehicle> list = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {

            while(rs.next()){
                list.add(mapRow(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        String sql = "SELECT * " +
                "FROM vehicles " +
                "WHERE price BETWEEN ? AND ? " +
                "ORDER BY price DESC; ";

        List<Vehicle> list = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setDouble(1, min);
            ps.setDouble(2, max);

            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        String sql = "SELECT * " +
                "FROM vehicles " +
                "WHERE mileage BETWEEN ? AND ? " +
                "ORDER BY mileage DESC; ";

        List<Vehicle> list = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, min);
            ps.setInt(2, max);

            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Vehicle> getVehiclesByYear(int min, int max) {
        String sql = "SELECT * " +
                "FROM vehicles " +
                "WHERE year BETWEEN ? AND ? " +
                "ORDER BY year DESC; ";

        List<Vehicle> list = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, min);
            ps.setInt(2, max);

            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Vehicle> getVehiclesByColor(String color) {
        String sql = "SELECT * " +
                "FROM vehicles " +
                "WHERE type LIKE ? " +
                "ORDER BY year DESC; ";
        List<Vehicle> list = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, color);

            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Vehicle> searchByModel(String model) {

        String sql = """
            SELECT *
            FROM vehicles
            WHERE model LIKE ?
            ORDER BY year DESC;
        """;

        List<Vehicle> list = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, model + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Vehicle> searchByType(String type) {

        String sql = """
            SELECT *
            FROM vehicles
            WHERE type = ?
            ORDER BY year DESC;
        """;

        List<Vehicle> list = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, type);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

