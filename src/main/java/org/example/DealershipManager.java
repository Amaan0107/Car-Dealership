package org.example;
import java.io.*;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DealershipManager {
    private static final String FILE_NAME = "inventory.csv";

    public Dealership getDealership() {
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String firstLine = br.readLine();
            if(firstLine == null){
               throw new IOException("File not found");
            }
            String[] dealershipInfo = firstLine.split("\\|");
            String name = dealershipInfo[0];
            String address = dealershipInfo[1];
            String phone = dealershipInfo[2];

            Dealership dealership = new Dealership(name, address, phone);

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if(parts.length == 8){
                    int vin = Integer.parseInt(parts[0]);
                    double price = Double.parseDouble(parts[1]);
                    int year = Integer.parseInt(parts[2]);
                    String model = parts[3];
                    String type = parts[4];
                    String color = parts[5];
                    int odometer = Integer.parseInt(parts[6]);

                    Vehicle v = new Vehicle(vin, year , model, type, color, odometer, (int) price);
                    dealership.addVehicle(v);
                }
            }
            return dealership;
        } catch (IOException e) {
            System.out.println("Error reading file");
            return null;
        }
    }

    public void saveDealership(Dealership dealership) {
    try(PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
        writer.printf("%s|%s|%s%n",
                dealership.getName(),
                dealership.getAddress(),
                dealership.getPhone());
        for (Vehicle v : dealership.getAllVehicles()) {
            writer.printf("%d|%d|%s|%s|%s|%s|%d|%.2f%n", v.getVin(), v.getYear(), v.getModel(), v.getType(),
                    v.getColor(), v.getOdometer(), v.getPrice());
        }
    } catch (IOException e) {
        System.out.println("Error saving file");
    }
    }
}
