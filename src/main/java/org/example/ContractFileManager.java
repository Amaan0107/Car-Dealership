package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    private static final String CONTRACT_FILE = "contracts.txt";

    public void saveContract(Contract contract) {
        try (FileWriter writer = new FileWriter(CONTRACT_FILE, true)) {
            Vehicle v = contract.getVehicleSold();

            if (contract instanceof SalesContract sc) {
                writer.write(String.format(
                        "SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%s|%.2f%n",
                        sc.getDateOfContract(),
                        sc.getCustomerName(),
                        sc.getCustomerEmail(),
                        v.getVin(),
                        v.getYear(),
                        v.getModel(),
                        v.getType(),
                        v.getColor(),
                        "N/A",
                        v.getOdometer(),
                        v.getPrice(),
                        v.getPrice() * 0.05,
                        100.0,
                        (v.getPrice() < 10000) ? 295.0 : 495.0,
                        sc.getTotalPrice(),
                        sc.isFinanceOption() ? "YES" : "NO",
                        sc.getMonthlyPayment()
                ));
            } else if (contract instanceof LeaseContract lc) {
                writer.write(String.format(
                        "LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f%n",
                        lc.getDateOfContract(),
                        lc.getCustomerName(),
                        lc.getCustomerEmail(),
                        v.getVin(),
                        v.getYear(),
                        v.getModel(),
                        v.getType(),
                        v.getColor(),
                        "N/A",
                        v.getOdometer(),
                        v.getPrice(),
                        v.getPrice() * 0.5,
                        v.getPrice() * 0.07,
                        lc.getTotalPrice(),
                        lc.getMonthlyPayment()
                ));
            }

            System.out.println("Contract saved to file successfully!");
        } catch (IOException e) {
            System.out.println("Error saving contract: " + e.getMessage());
        }
    }
}
