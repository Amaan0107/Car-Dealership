package org.example;

import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner = new Scanner(System.in);
    private final VehicleDao vehicleDao;

    public UserInterface() {

        // Create the DataSource
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/cardealership");
        dataSource.setUsername("root");
        dataSource.setPassword("Feroze1975$");

        // DAO uses the modern DataSource
        vehicleDao = new VehicleDao(dataSource);
    }

    public void showMenu() {

        boolean running = true;

        while (running) {
            System.out.println("\nWelcome to Car Dealership");
            System.out.println("-------- Dealership Menu --------");
            System.out.println("1) Search by Price Range");
            System.out.println("2) Search by Model");
            System.out.println("3) Search by Year Range");
            System.out.println("4) Search by Color");
            System.out.println("5) Search by Mileage Range");
            System.out.println("6) Search by Type");
            System.out.println("7) Show All Vehicles");
            System.out.println("0) Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> searchByPrice();
                case "2" -> searchByModel();
                case "3" -> searchByYearRange();
                case "4" -> searchByColor();
                case "5" -> searchByMileage();
                case "6" -> searchByType();
                case "7" -> showAllVehicles();
                case "0" -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void searchByPrice() {
        try {
            System.out.print("Enter minimum price: ");
            double min = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Enter maximum price: ");
            double max = Double.parseDouble(scanner.nextLine().trim());

            displayVehicles(vehicleDao.getVehiclesByPrice(min, max));

        } catch (Exception e) {
            System.out.println("Invalid input. Please enter valid numbers.");
        }
    }

    private void searchByModel() {
        System.out.print("Enter model: ");
        String model = scanner.nextLine().trim();

        displayVehicles(vehicleDao.searchByModel(model));
    }

    private void searchByYearRange() {
        try {
            System.out.print("Enter start year: ");
            int start = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter end year: ");
            int end = Integer.parseInt(scanner.nextLine().trim());

            displayVehicles(vehicleDao.getVehiclesByYear(start, end));

        } catch (NumberFormatException e) {
            System.out.println("Invalid year input.");
        }
    }

    private void searchByColor() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine().trim();

        displayVehicles(vehicleDao.getVehiclesByColor(color));
    }

    private void searchByMileage() {
        try {
            System.out.print("Enter minimum mileage: ");
            int min = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter maximum mileage: ");
            int max = Integer.parseInt(scanner.nextLine().trim());

            displayVehicles(vehicleDao.getVehiclesByMileage(min, max));

        } catch (NumberFormatException e) {
            System.out.println("Invalid mileage input.");
        }
    }

    private void searchByType() {
        System.out.print("Enter type (SUV, Truck, Sedan, Coupe, etc): ");
        String type = scanner.nextLine().trim();

        displayVehicles(vehicleDao.searchByType(type));
    }

    private void showAllVehicles() {
        displayVehicles(vehicleDao.getAllVehicles());
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("There are no vehicles in the dealership");
            return;
        }

        System.out.println("----------------- Inventory ----------------");
        System.out.printf("%-18s | %-6s | %-10s | %-10s | %-10s | %-10s | %-8s%n",
                "VIN", "Year", "Model", "Type", "Color", "Price", "Miles");
        System.out.println("-----------------------------------------------------------------------------------");

        for (Vehicle v : vehicles) {
            System.out.printf("%-18s | %-6d | %-10s | %-10s | %-10s | $%-9.2f | %-8d%n",
                    v.getVin(),       // <-- STRING now
                    v.getYear(),
                    v.getModel(),
                    v.getType(),
                    v.getColor(),
                    v.getPrice(),
                    v.getOdometer());
        }
    }
    public void showIntro(){
        String[] intro = {
                "__          __  _                            _ \n" +
                        "\\ \\        / / | |                          | |\n" +
                        " \\ \\  /\\  / /__| | ___ ___  _ __ ___   ___  | |\n" +
                        "  \\ \\/  \\/ / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ | |\n" +
                        "   \\  /\\  /  __/ | (_| (_) | | | | | |  __/ |_|\n" +
                        "    \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___| (_)\n" +
                        "                                               \n" +
                        "           \uD83D\uDE97  to Amaan's Dealership  \uD83D\uDE97"};
        for (String line : intro) {
            System.out.println(line);
            try { Thread.sleep(300); } catch (InterruptedException e) {}
        }

        System.out.print("\nLoading inventory");
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
        } catch (InterruptedException e) {}

        System.out.println("\n System Ready!\n");
    }

}
