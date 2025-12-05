package org.example;

public class Vehicle {
    private String vin;
    private int year;
    private String model;
    private String type;
    private String color;
    private int odometer;
    private double price;

    public Vehicle(String vin, int year, String model, String type, String color, double price, int odometer) {
        this.vin = vin;
        this.year = year;
        this.model = model;
        this.type = type;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }

    public String getVin() {
        return vin;
    }

    public int getYear() {
        return year;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public int getOdometer() {
        return odometer;
    }

    public double getPrice() {
        return price;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return String.format("%d | %d | %s | %s | %s | $%.2f | %d miles",
                vin, year, model, type, color, price, odometer);
    }
}



