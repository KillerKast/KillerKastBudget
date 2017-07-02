package com.killerkast.entities;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentPlan extends Bill {

    private Integer paymentDate;
    private BigDecimal startingBalance;
    private Date lastPaymentDate;

    public PaymentPlan(String name, String description, BigDecimal paymentAmount, Integer paymentDate, BigDecimal startingBalance, Date lastPaymentDate) {
        super(name, description, paymentAmount);
        this.paymentDate = paymentDate;
        this.startingBalance = startingBalance;
        this.lastPaymentDate = lastPaymentDate;
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

    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(Date lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
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
                ", lastPaymentDate: " + this.getLastPaymentDate() +
                "}}";
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o)) return false;
        if (this == o) return true;
        if (!(o instanceof PaymentPlan)) return false;
        if (!super.equals(o)) return false;

        PaymentPlan that = (PaymentPlan) o;

        if (!getPaymentDate().equals(that.getPaymentDate())) return false;
        if (!getStartingBalance().equals(that.getStartingBalance())) return false;
        return getLastPaymentDate().equals(that.getLastPaymentDate());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getPaymentDate().hashCode();
        result = 31 * result + getStartingBalance().hashCode();
        result = 31 * result + getLastPaymentDate().hashCode();
        return result;
    }
}

