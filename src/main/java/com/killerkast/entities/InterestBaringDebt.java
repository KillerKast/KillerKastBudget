package com.killerkast.entities;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document
public class InterestBaringDebt extends Bill {

    private Integer paymentDate;
    private BigDecimal startingBalance;
    private BigDecimal apr;

    @DBRef
    private List<InterestBaringPaymentSchedule> interestBaringPaymentSchedules;

    public InterestBaringDebt() {}

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

    public List<InterestBaringPaymentSchedule> getInterestBaringPaymentSchedules() {
        return interestBaringPaymentSchedules;
    }

    public void setInterestBaringPaymentSchedules(List<InterestBaringPaymentSchedule> interestBaringPaymentSchedules) {
        this.interestBaringPaymentSchedules = interestBaringPaymentSchedules;
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
