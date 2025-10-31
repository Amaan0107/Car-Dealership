package org.example;
import java.io.*;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DealershipManager {
    private static final String FILE_NAME = "Inventory.csv";


    public Dealership loadDealership() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String firstLine = br.readLine();
            if (firstLine == null || firstLine.trim().isEmpty()) {
                throw new IOException("File is empty or missing dealership info");
            }

            String[] dealershipInfo = firstLine.split("\\|");
            if (dealershipInfo.length < 3) {
                throw new IOException("Invalid dealership info format. Expected: name|address|phone");
            }

            String name = dealershipInfo[0];
            String address = dealershipInfo[1];
            String phone = dealershipInfo[2];
            Dealership dealership = new Dealership(name, address, phone);

            // Skip header line if present
            String line = br.readLine();
            if (line != null && line.startsWith("VIN")) {
                line = br.readLine();
            }


            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length != 7) {
                    continue;
                }

                try {
                    int vin = Integer.parseInt(parts[0].trim());
                    int year = Integer.parseInt(parts[1].trim());
                    String model = parts[2].trim();
                    String type = parts[3].trim();
                    String color = parts[4].trim();
                    double price = Double.parseDouble(parts[5].trim());
                    int odometer = Integer.parseInt(parts[6].trim());

                    Vehicle v = new Vehicle(vin, year, model, type, color, price, odometer);
                    dealership.addVehicle(v);
                } catch (NumberFormatException e) {
                    System.out.println("Skipped bad data line: " + line);
                }
            }

            return dealership;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    public void saveDealership(Dealership dealership) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.printf("%s|%s|%s%n",
                    dealership.getName(),
                    dealership.getAddress(),
                    dealership.getPhone());

            writer.println("VIN|Year|Model|Type|Color|Price|Miles");

            for (Vehicle v : dealership.getAllVehicles()) {
                writer.printf("%d|%d|%s|%s|%s|%.2f|%d%n",
                        v.getVin(),
                        v.getYear(),
                        v.getModel(),
                        v.getType(),
                        v.getColor(),
                        v.getPrice(),
                        v.getOdometer());
            }

            System.out.println("Dealership data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
