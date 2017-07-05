package com.killerkast.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Document
public class InterestBaringPaymentSchedule {

    @Id
    private String id;
    private LocalDate date;
    private BigDecimal interestOwed;
    private BigDecimal owed;

    @DBRef
    private InterestBaringDebt interestBaringDebt;

    public InterestBaringPaymentSchedule(){};

    public InterestBaringPaymentSchedule(LocalDate date, BigDecimal interestOwed, BigDecimal owed, InterestBaringDebt interestBaringDebt){
        this.date = date;
        this.interestOwed = interestOwed;
        this.owed = owed;
        this.interestBaringDebt = interestBaringDebt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getInterestOwed() {
        return interestOwed;
    }

    public void setInterestOwed(BigDecimal interestOwed) {
        this.interestOwed = interestOwed;
    }

    public BigDecimal getOwed() {
        return owed;
    }

    public void setOwed(BigDecimal owed) {
        this.owed = owed;
    }



}
