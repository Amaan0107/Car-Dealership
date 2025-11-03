package org.example;

public class SalesContract extends Contract{
    private boolean isFinanceOption;

    public SalesContract(String dateOfContract, String customerName,
                         String customerEmail, String vehicleSold, boolean isFinanceOption){
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.isFinanceOption = isFinanceOption;
    }


    @Override
    public double calculateMonthlyPayment() {

    }

    @Override
    public double calculateTotalPayment() {

    }
}
