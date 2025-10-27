package org.example;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        dealership = new Dealership("Amaan's dealership", "123 Poppy St", "925-435-7324");

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

            String choice = scanner.nextLine();

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
        dealership = dfm.getDealership();
        System.out.println("Dealership loaded successfully!");
    }

    public void getByPrice() {
        try {

            System.out.printf("Enter min price:");
            double min = scanner.nextDouble();
            System.out.printf("Enter max price:");
            double max = scanner.nextDouble();

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
        if (vehicles.isEmpty()) {
            System.out.println("There are no vehicles in the dealership");
        } else {
            System.out.println("-----------------Inventory----------------");
            for (Vehicle v : vehicles) {
                System.out.printf("%d | %d | %s | %s | %s |  $%.2f | %d miles%n",
                        v.getVin(), v.getYear(), v.getModel(),
                        v.getType(), v.getColor(), v.getOdometer(),
                        v.getPrice());
            }
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
                System.out.println("Vehicle removed successfully!");
            } else {
                System.out.println("Invalid VIN number");
            }
            scanner.nextLine();
        }catch (InputMismatchException e){
            System.out.println("Invalid input");
        }
    }

    private void addVehicle() {
        try {


            System.out.println("Adding vehicle to dealership");

            System.out.print("Enter VIN num:");
            int vin = scanner.nextInt();

            System.out.print("Enter year:");
            int year = scanner.nextInt();

            System.out.print("Enter model:");
            String model = scanner.next();

            System.out.print("Enter Type:");
            String type = scanner.next();

            System.out.print("Enter color:");
            String color = scanner.next();

            System.out.print("Enter odometer:");
            double odometer = scanner.nextDouble();

            System.out.print("Enter price:");
            double price = scanner.nextDouble();

            scanner.nextLine();

            Vehicle v = new Vehicle(vin, year, model, type, color, odometer, (int) price);
            dealership.addVehicle(v);
            System.out.println("Vehicle added successfully!");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input type. Please enter numbers where required.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            scanner.nextLine();
        }finally {
            scanner.nextLine();
        }
    }
}
