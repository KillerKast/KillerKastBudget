package com.killerkast.services;

import com.killerkast.entities.MonthlyBill;
import com.killerkast.service.interfaces.BillServiceFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.Month;

/**
 * Created by killerkast on 7/1/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MonthlyBillTest {

    @Autowired
    BillServiceFactory billServiceFactory;

    @Test
    public void TestMonthlyBill(){
        /**Test Create**/
        MonthlyBill cmb = (MonthlyBill)billServiceFactory.createBill(creaeteMonthlyBill(), "monthly-bill");
        MonthlyBill tmb = (MonthlyBill)billServiceFactory.readBill(cmb.getId(), "monthly-bill");
        Assert.assertTrue(cmb.equals(tmb));

        /**Test Update**/
        MonthlyBill umb = (MonthlyBill)billServiceFactory.updateBill(updateMonnthlyBill(tmb), "monthly-bill");
        MonthlyBill urmb = (MonthlyBill)billServiceFactory.readBill(umb.getId(), "monthly-bill");
        Assert.assertTrue(umb.equals(urmb));

        /**Test Delete**/
        billServiceFactory.deleteBill(umb.getId(), "monthly-bill");
        Assert.assertNull(billServiceFactory.readBill(umb.getId(), "monthly-bill"));
    }

    private MonthlyBill creaeteMonthlyBill(){
        return new MonthlyBill(
                "MonthlyBillTest",
                "Monthly Bill Test",
                new BigDecimal("123.45"),
                12);
    }

    private MonthlyBill updateMonnthlyBill(MonthlyBill monthlyBill){
        monthlyBill.setName("UpdateBillTest");
        monthlyBill.setDescription("Update Bill Test");
        monthlyBill.setPaymentAmount(new BigDecimal("234.56"));
        monthlyBill.setPaymentDate(23);
        return monthlyBill;
    }
}


