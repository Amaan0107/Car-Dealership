package org.example;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private final Scanner scanner = new Scanner(System.in);
    private PrintWriter writer;

    public void showMenu() {
        dealership = new Dealership("Amaan's dealership", "123 Poppy St", "925-435-7324");
        DealershipManager dm = new DealershipManager();
        dealership = dm.loadDealership();

        if (dealership == null) {
            dealership = new Dealership("Amaan's dealership", "123 Poppy St", "925-435-7324");
            System.out.println("Created new dealership (no existing inventory found).");
        } else {
            System.out.println("Dealership loaded successfully!");
        }
        boolean running = true;
        while (running) {

            System.out.println("Welcome to Car Dealership");
            System.out.println("--------Dealership Menu--------");
            System.out.println("1) Search by price ");
            System.out.println("2) Add Vehicle ");
            System.out.println("3) Remove Vehicle ");
            System.out.println("4) Show All Vehicles");
            System.out.println("5) Exit");
            System.out.println("------------------------------");
            System.out.printf("Enter here:");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> getByPrice();
                case "2" -> addVehicle();
                case "3" -> removeVehicle();
                case "4" -> showAllVehicles();
                case "5" -> {
                    System.out.println("Goodbye !!");
                    running = false;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    void init() {
        DealershipManager dfm = new DealershipManager();
        dealership = dfm.loadDealership();
    }

    public void getByPrice() {
        try {
            System.out.print("Enter min price: ");
            double min = Double.parseDouble(scanner.nextLine().replace(",", "").trim());

            System.out.print("Enter max price: ");
            double max = Double.parseDouble(scanner.nextLine().replace(",", "").trim());


            List<Vehicle> results = dealership.getVehiclesByPrice(min, max);
            displayVehicles(results);
        }catch (InputMismatchException e){
            System.out.println("Invalid input");
        }finally {
            scanner.nextLine();
        }
    }
    private void showAllVehicles() {
        try {


            List<Vehicle> vehicles = dealership.getAllVehicles();
            displayVehicles(vehicles);
        }catch (InputMismatchException e){
            System.out.println("No cars in Inventory");
        }finally {
            scanner.nextLine();
        }
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("There are no vehicles in the dealership");
            return;
        }

        System.out.println("-----------------Inventory----------------");
        System.out.printf("%-8s | %-6s | %-10s | %-10s | %-10s | %-10s | %-8s%n",
                "VIN", "Year", "Model", "Type", "Color", "Price", "Miles");
        System.out.println("-----------------------------------------------------------------------");

        for (Vehicle v : vehicles) {
            System.out.printf("%-8d | %-6d | %-10s | %-10s | %-10s | $%-9.2f | %-8d%n",
                    v.getVin(),
                    v.getYear(),
                    v.getModel(),
                    v.getType(),
                    v.getColor(),
                    v.getPrice(),
                    v.getOdometer());
        }
    }

    private void removeVehicle() {
        try {
            System.out.println("Removing vehicle from dealership");

            System.out.print("Enter VIN num to remove:");
            int vin = scanner.nextInt();

            Vehicle toRemove = null;
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                if (vehicle.getVin() == vin) {
                    toRemove = vehicle;
                    break;
                }
            }
            if (toRemove != null) {
                dealership.removeVehicle(toRemove);

                DealershipManager dm = new DealershipManager();
                dm.saveDealership(dealership);

                System.out.println("Vehicle removed successfully!");
            } else {
                System.out.println("Invalid VIN number");
            }
            scanner.nextLine().trim();
        }catch (InputMismatchException e){
            System.out.println("Invalid input");
        }
    }

    private void addVehicle() {
        try {
            System.out.println("Adding vehicle to dealership");

            System.out.print("Enter VIN num:");
            int vin = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter year:");
            int year = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter model:");
            String model = scanner.nextLine().trim();

            System.out.print("Enter type:");
            String type = scanner.nextLine().trim();

            System.out.print("Enter color:");
            String color = scanner.nextLine().trim();

            System.out.print("Enter odometer:");
            double odometer = Double.parseDouble(scanner.nextLine().replace(",", "").trim());

            System.out.print("Enter price:");
            double price = Double.parseDouble(scanner.nextLine().replace(",", "").trim());

            Vehicle v = new Vehicle(vin, year, model, type, color, price, (int) odometer);
            dealership.addVehicle(v);

            System.out.println("Vehicle added successfully!");

            DealershipManager dm = new DealershipManager();
            dm.saveDealership(dealership);

            System.out.println("Vehicle added and saved successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please don’t include letters or symbols.");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
