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

        boolean running = true;
        while (running) {

            System.out.println("Welcome to Car Dealership");
            System.out.println("--------Dealership Menu--------");
            System.out.println("1) Search by Price ");
            System.out.println("2) Search by Model ");
            System.out.println("3) Search by year range ");
            System.out.println("4) Search by Color ");
            System.out.println("5) Search by Mileage ");
            System.out.println("6) Search by Type ");
            System.out.println("7) Add Vehicle ");
            System.out.println("8) Remove Vehicle ");
            System.out.println("9) Create Sales Contract");
            System.out.println("10) Create Lease Contract");
            System.out.println("11) Show All Vehicles");
            System.out.println("0) Exit");
            System.out.println("------------------------------");
            System.out.printf("Enter here:");

            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> getByPrice();
                    case "2" -> getByModel();
                    case "3" -> getByYearRange();
                    case "4" -> getByColor();
                    case "5" -> getByMileage();
                    case "6" -> getByType();
                    case "7" -> addVehicle();
                    case "8" -> removeVehicle();
                    case "9" -> createSalesContract();
                    case "10" -> createLeaseContract();
                    case "11" -> showAllVehicles();
                    case "0" -> {
                        System.out.println("Goodbye !!");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice");
                }
            }catch (InputMismatchException ime) {
                System.out.println("Please enter a valid choice");
            }
        }
    }
    public void createSalesContract() {
        try {
            System.out.println("Enter Vehicle VIN num:");
            String vin = scanner.nextLine().trim();

            for (Vehicle v : dealership.getAllVehicles()) {
                if (v.getVin() == vin) chosen = v;
            }

            if (chosen == null) {
                System.out.println("Vehicle not found.");
                return;
            }
        }
    }

    void init() {

        DealershipManager dfm = new DealershipManager();
        dealership = dfm.loadDealership();

    }
    public void getByModel() {
        try{

            System.out.print("Enter Model: ");
            String model = scanner.nextLine().trim();

            List<Vehicle> results = dealership.getVehiclesByModel(model);
            displayVehicles(results);

            if (results.isEmpty()) {
                System.out.println("We do not have vehicles in that Model.");
            }

        }catch(InputMismatchException e){

            System.out.println("Invalid Model");

        } finally {

            scanner.nextLine();
        }

    }
    public void getByYearRange() {
        try {
            System.out.print("From: ");
            int  from = scanner.nextInt();

            System.out.print("To: ");
            int to = scanner.nextInt();


            List<Vehicle> results = dealership.getVehiclesByYear(from, to);
            displayVehicles(results);

            if (results.isEmpty()) {
                System.out.println("We do not have vehicles in that range.");
            }

        }catch (InputMismatchException e){
            System.out.println("Invalid input");
        }finally {
            scanner.nextLine();
        }
    }
    public void getByColor() {
        try {
            System.out.print("Color: ");
            String color = scanner.nextLine().trim();

            List<Vehicle> results = dealership.getVehiclesByColor(color);
            displayVehicles(results);

            if (results.isEmpty()) {
                System.out.println("We do not have vehicles in that color.");
            }

        }catch (InputMismatchException e){
            System.out.println("Invalid input");
        }finally {
            scanner.nextLine();
        }
    }
    public void getByMileage() {
        try {
            System.out.print("Max mileage: ");
            int maxMileage = scanner.nextInt();
            System.out.print("Min mileage: ");
            int minMileage = scanner.nextInt();

            List<Vehicle> results = dealership.getVehiclesByMilage(minMileage, maxMileage);
            displayVehicles(results);

            if (results.isEmpty()) {
                System.out.println("We do not have vehicles in that range.");
            }

        }catch (InputMismatchException e){
            System.out.println("Invalid input");
        }finally {
            scanner.nextLine();
        }
    }
    public void getByType(){
        try {
            System.out.print("Type: ");
            String type = scanner.nextLine().trim();

            List<Vehicle> results = dealership.getVehiclesByType(type);
            displayVehicles(results);

            if (results.isEmpty()) {
                System.out.println("We do not have vehicles in that Type.");
            }

        }catch (InputMismatchException e){
            System.out.println("Invalid input");
        }finally {
            scanner.nextLine();
        }
    }

    public void getByPrice() {
        try {
            System.out.print("Enter min price: ");
            double min = Double.parseDouble(scanner.nextLine().replace(",", "").trim());

            System.out.print("Enter max price: ");
            double max = Double.parseDouble(scanner.nextLine().replace(",", "").trim());


            List<Vehicle> results = dealership.getVehiclesByPrice(min, max);
            displayVehicles(results);

            if (results.isEmpty()) {
                System.out.println("We do not have vehicles in that range.");
            }

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
