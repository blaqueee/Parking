package com;

public class Payment {
    private String number;
    private int forPay;

    public Payment(String number, int forPay) {
        this.number = number;
        this.forPay = forPay;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getForPay() {
        return forPay;
    }

    public void setForPay(int forPay) {
        this.forPay = forPay;
    }
}
