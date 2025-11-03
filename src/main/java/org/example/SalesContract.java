package org.example;

public class SalesContract extends Contract{
    private boolean isFinanceOption;

    public SalesContract(String dateOfContract, String customerName,
                         String customerEmail, Vehicle vehicleSold, boolean isFinanceOption){
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.isFinanceOption = isFinanceOption;
    }


    @Override
    public double calculateMonthlyPayment() {
        double totalPayment = getTotalPrice();
        double rate = 0;
        int months = 1;
        if(totalPayment >= 10000) {
            rate = 0.0425;
            months = 48;
        }else {

            rate = 0.0525;
            months = 24;
        }
        return (totalPayment * (rate / 12)) / (1 - Math.pow(1 + (rate / 12), -months));

    }

    @Override
    public double calculateTotalPayment() {
        double price = getVehicleSold().getPrice();
        double tax = price * 0.05;
        double recordingFee = 100;
        double processingFee = 0;
        if(price <= 10000) {
            processingFee = 295;
        }else{
            processingFee = 495;
        }
        return price + tax + recordingFee + processingFee;
    }
    public boolean isFinanceOption() {
        return isFinanceOption;
    }
}
