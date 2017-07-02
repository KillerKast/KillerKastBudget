package com.killerkast.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = InterestBaringDebt.class, name="interest-baring-debt"),
        @JsonSubTypes.Type(value = MonthlyBill.class, name="monthly-bill"),
        @JsonSubTypes.Type(value = NoInterestDebt.class, name="no-interest-debt"),
        @JsonSubTypes.Type(value = OneTimeBill.class, name="one-time-bill"),
        @JsonSubTypes.Type(value = PaymentPlan.class, name="payment-plan"),
        @JsonSubTypes.Type(value = YearlyBill.class, name="yearly-bill"),
})
public abstract class Bill {

    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal paymentAmount;

    public Bill() {}

    public Bill(String name, String description, BigDecimal paymentAmount) {
        this.name = name;
        this.description = description;
        this.paymentAmount = paymentAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bill)) return false;

        Bill bill = (Bill) o;

        if (getId() != null ? !getId().equals(bill.getId()) : bill.getId() != null) return false;
        if (getName() != null ? !getName().equals(bill.getName()) : bill.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(bill.getDescription()) : bill.getDescription() != null)
            return false;
        return getPaymentAmount() != null ? getPaymentAmount().equals(bill.getPaymentAmount()) : bill.getPaymentAmount() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getPaymentAmount() != null ? getPaymentAmount().hashCode() : 0);
        return result;
    }
}