package com.killerkast.entities;

import java.math.BigDecimal;

public class InterestBaringDebt extends Bill {

    private Integer paymentDate;
    private BigDecimal startingBalance;
    private BigDecimal apr;

    public InterestBaringDebt(String name, String description, BigDecimal paymentAmount, Integer paymentDate, BigDecimal startingBalance, BigDecimal apr) {
        super(name, description, paymentAmount);
        this.paymentDate = paymentDate;
        this.startingBalance = startingBalance;
        this.apr = apr;
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

    public BigDecimal getApr() {
        return apr;
    }

    public void setApr(BigDecimal apr) {
        this.apr = apr;
    }

    @Override
    public String toString() {
        return "{" +
                "Interest Baring Debt:{" +
                "id: " + this.getId() +
                ", name: " + this.getName() +
                ", description: " + this.getDescription() +
                ", paymentAmount: " + this.getPaymentAmount().toString() +
                ", paymentDate: " + this.getPaymentDate() +
                ", startingBalance: " + this.getStartingBalance() +
                ", APR: " + this.getApr() +
                "}}";
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o)) return false;
        if (this == o) return true;
        if (!(o instanceof InterestBaringDebt)) return false;

        InterestBaringDebt that = (InterestBaringDebt) o;

        if (!getPaymentDate().equals(that.getPaymentDate())) return false;
        if (!getStartingBalance().equals(that.getStartingBalance())) return false;
        return getApr().equals(that.getApr());
    }

    @Override
    public int hashCode() {
        int result = getPaymentDate().hashCode();
        result = 31 * result + getStartingBalance().hashCode();
        result = 31 * result + getApr().hashCode();
        return result;
    }
}
