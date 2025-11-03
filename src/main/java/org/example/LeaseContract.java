package org.example;

public class LeaseContract extends Contract{

    public LeaseContract(String dateOfContract, String customerName,
                         String customerEmail, String vehicleSold) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
    }

    @Override
    public double calculateMonthlyPayment() {

    }

    @Override
    public double calculateTotalPayment() {

    }
}
