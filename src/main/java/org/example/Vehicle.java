package org.example;

public class Vehicle {
    private int vin;
    private int year;
    private String model;
    private String type;
    private String color;
    private int odometer;
    private double price;

    public Vehicle(int vin, int year, String model, String type, String color, double price, int odometer) {
        this.vin = vin;
        this.year = year;
        this.model = model;
        this.type = type;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }

    public int getVin() {
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

    public void setVin(int vin) {
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
        return vin + " | " + year + " | " + model + " | " + type + " | " + color + " | " + odometer + " | " + price;
    }
}



