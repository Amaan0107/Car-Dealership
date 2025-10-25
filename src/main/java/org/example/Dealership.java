package org.example;
import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;

    private ArrayList<Vehicle> vehicles;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.vehicles = new ArrayList<>();
    }
    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle v : vehicles) {
            if (v.getPrice() >= min && v.getPrice() <= max) {
               results.add(v);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByModel(String model) {
        List<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle v : vehicles) {
            if (v.getModel().equalsIgnoreCase(model)) {
                results.add(v);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle v : vehicles) {
            if (v.getColor().equalsIgnoreCase(color)) {
                results.add(v);
            }
        }
        return results;
    }
    public List<Vehicle> getVehiclesByYear(int min, int max) {
        List<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle v : vehicles) {
            if (v.getYear() >= min && v.getYear() <= max) {
                results.add(v);
            }
        }
        return results;
    }
    public List<Vehicle> getVehiclesByMilage(int min, int max) {
        List<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle v : vehicles) {
            if (v.getOdometer() >= min && v.getOdometer() <= max) {
                results.add(v);
            }
        }
        return results;
    }
    public List<Vehicle> getVehiclesByType(String type) {
        List<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle v : vehicles) {
            if (v.getType().equalsIgnoreCase(type)) {
                results.add(v);
            }
        }
        return results;
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return new ArrayList<>(vehicles);
    }
    public  void addVehicle(Vehicle v) {
        vehicles.add(v);
    }
    public  void removeVehicle(Vehicle v) {
        vehicles.remove(v);
    }
}
