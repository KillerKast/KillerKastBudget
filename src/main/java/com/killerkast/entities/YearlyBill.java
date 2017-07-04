package com.killerkast.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
public class YearlyBill extends Bill {

    private Integer paymentMonth;
    private Integer paymentDay;

    public YearlyBill() {}

    public YearlyBill(String name, String description, BigDecimal paymentAmount, Integer paymentMonth, Integer paymentDay) {
        super(name, description, paymentAmount);

        this.paymentMonth = paymentMonth;
        this.paymentDay = paymentDay;
    }

    public Integer getPaymentMonth() {
        return paymentMonth;
    }

    public void setPaymentMonth(Integer paymentMonth) {
        this.paymentMonth = paymentMonth;
    }

    public Integer getPaymentDay() {
        return paymentDay;
    }

    public void setPaymentDay(Integer paymentDay) {
        this.paymentDay = paymentDay;
    }

    @Override
    public String toString() {
        return "{" +
                "Yearly Bill:{" +
                "id: " + this.getId() +
                ", name: " + this.getName() +
                ", description: " + this.getDescription() +
                ", paymentAmount: " + this.getPaymentAmount().toString() +
                ", paymentMonth: " + this.getPaymentMonth().toString() +
                ", paymentDay: " + this.getPaymentDay().toString() +
                "}}";
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o)) return false;
        if (this == o) return true;
        if (!(o instanceof YearlyBill)) return false;
        if (!super.equals(o)) return false;

        YearlyBill that = (YearlyBill) o;

        if (!getPaymentMonth().equals(that.getPaymentMonth())) return false;
        return getPaymentDay().equals(that.getPaymentDay());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getPaymentMonth().hashCode();
        result = 31 * result + getPaymentDay().hashCode();
        return result;
    }
}
