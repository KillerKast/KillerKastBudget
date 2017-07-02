package com.killerkast.entities;

import java.math.BigDecimal;

public class NoInterestDebt extends Bill {

    private Integer paymentDate;
    private BigDecimal startingBalance;

    public NoInterestDebt(){}

    public NoInterestDebt(String name, String description, BigDecimal paymentAmount, Integer paymentDate, BigDecimal startingBalance) {
        super(name, description, paymentAmount);
        this.paymentDate = paymentDate;
        this.startingBalance = startingBalance;
    }

    public Integer getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Integer paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getStartingBalance() {
        return startingBalance;
    }

    public void setStartingBalance(BigDecimal startingBalance) {
        this.startingBalance = startingBalance;
    }

    @Override
    public String toString() {
        return "{" +
                "No Interest Debt:{" +
                "id: " + this.getId() +
                ", name: " + this.getName() +
                ", description: " + this.getDescription() +
                ", paymentAmount: " + this.getPaymentAmount().toString() +
                ", paymentMonth: " + this.getStartingBalance().toString() +
                ", paymentDay: " + this.getPaymentDate() +
                "}}";
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o)) return false;
        if (this == o) return true;
        if (!(o instanceof NoInterestDebt)) return false;
        if (!super.equals(o)) return false;

        NoInterestDebt that = (NoInterestDebt) o;

        if (!getPaymentDate().equals(that.getPaymentDate())) return false;
        return getStartingBalance().equals(that.getStartingBalance());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getPaymentDate().hashCode();
        result = 31 * result + getStartingBalance().hashCode();
        return result;
    }
}
