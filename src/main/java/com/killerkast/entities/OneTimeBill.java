package com.killerkast.entities;

import java.math.BigDecimal;
import java.util.Date;

public class OneTimeBill extends Bill {

    private Date paymentDate;

    public OneTimeBill(String name, String description, BigDecimal paymentAmount, Date paymentDate){
        super(name, description, paymentAmount);

        this.setPaymentDate(paymentDate);
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "{" +
                "One Time Bill:{" +
                "id: " + this.getId() +
                ", name: " + this.getName() +
                ", description: " + this.getDescription() +
                ", paymentAmount: " + this.getPaymentAmount().toString() +
                ", paymentDate: " + this.getPaymentDate().toString() +
                "}}";
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o)) return false;
        if (this == o) return true;
        if (!(o instanceof OneTimeBill)) return false;
        if (!super.equals(o)) return false;

        OneTimeBill that = (OneTimeBill) o;

        return getPaymentDate().equals(that.getPaymentDate());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getPaymentDate().hashCode();
        return result;
    }
}
