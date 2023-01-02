package com.codewithmosh;

public class MortgageCalculator {
    private final static byte MONTHS_IN_YEAR = 12;
    private final static byte PERCENT = 100;

    private double principal;
    private float annualInterest;
    private byte years;

    public MortgageCalculator(double principal, float annualInterest, byte years) {
        this.principal = principal;
        this.annualInterest = annualInterest;
        this.years = years;
    }

    public double calculateBalance( short numberOfPaymentsMade){
        float numbersOfPayments = getNumberOfPayments();
        float monthlyInterest = getMonthlyInterest();

        double balance = principal
                    * (Math.pow(1 + monthlyInterest, numbersOfPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                    / (Math.pow(1 + monthlyInterest, numbersOfPayments) - 1);

        return balance;
    }

    public double calculateMortgage(){
        short numbersOfPayments = (short) (getNumberOfPayments());
        float monthlyInterest = getMonthlyInterest();

        double mortgage = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest,numbersOfPayments))
                / (Math.pow(1 + monthlyInterest,numbersOfPayments) - 1);

        return mortgage;

    }

    public double[] getRemainingBalances(){
        var balances = new double[getNumberOfPayments()];
        for (short month = 1; month <= balances.length; month++)
            balances[month - 1] = calculateBalance(month);

        return balances;
    }

    private float getMonthlyInterest() {
        return annualInterest / PERCENT / MONTHS_IN_YEAR;
    }

    private int getNumberOfPayments() {
        return years * MONTHS_IN_YEAR;
    }


}
