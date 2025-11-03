package org.example;

public class LeaseContract extends Contract{

    public LeaseContract(String dateOfContract, String customerName,
                         String customerEmail, Vehicle vehicleSold) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
    }

    @Override
    public double calculateMonthlyPayment() {
    double total = getTotalPrice();
    double rate = 0.04;
    int months = 36;
    return (total * (rate / 12)) / (1 - Math.pow(1 + (rate / 12), -months));


    }

    @Override
    public double calculateTotalPayment() {
        double price = getVehicleSold().getPrice();
        double expectedEndPayment = price * 0.5;
        double leaseFee = price * 0.07;
        return price + expectedEndPayment + leaseFee;
    }
}
