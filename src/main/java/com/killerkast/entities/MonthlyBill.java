package com.killerkast.entities;

import java.math.BigDecimal;

public class MonthlyBill extends Bill {

    private Integer paymentDate;

    public MonthlyBill() {}

    public MonthlyBill(String name, String description, BigDecimal paymentAmount, Integer paymentDate) {
        super(name, description, paymentAmount);

        this.paymentDate = paymentDate;
    }
    
    public Integer getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Integer paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "{" +
                "Monthly Bill:{" +
                "id: " + this.getId() +
                ", name: " + this.getName() +
                ", description: " + this.getDescription() +
                ", paymentAmount: " + this.getPaymentAmount().toString() +
                ", paymentDate: " + this.getPaymentDate() +
                "}}";
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o)) return false;
        if (this == o) return true;
        if (!(o instanceof MonthlyBill)) return false;
        if (!super.equals(o)) return false;

        MonthlyBill that = (MonthlyBill) o;

        return getPaymentDate().equals(that.getPaymentDate());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getPaymentDate().hashCode();
        return result;
    }
}
