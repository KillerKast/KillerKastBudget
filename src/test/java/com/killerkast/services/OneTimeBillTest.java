package com.killerkast.services;

import com.killerkast.entities.OneTimeBill;
import com.killerkast.service.interfaces.BillServiceFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OneTimeBillTest {
    @Autowired
    BillServiceFactory billServiceFactory;

    @Test
    public void TestOneTimeBill(){
        /**Test Create**/
        OneTimeBill ctb = (OneTimeBill)billServiceFactory.createBill(createOneTimeBill(), "one-time-bill");
        OneTimeBill rctb = (OneTimeBill)billServiceFactory.readBill(ctb.getId(), "one-time-bill");
        Assert.assertTrue(ctb.equals(rctb));

        /**Test Update**/
        OneTimeBill utb = (OneTimeBill)billServiceFactory.updateBill(updateOneTimeBill(rctb), "one-time-bill");
        OneTimeBill uctb = (OneTimeBill)billServiceFactory.readBill(utb.getId(), "one-time-bill");
        Assert.assertTrue(utb.equals(uctb));

        /**Test Delete**/
        billServiceFactory.deleteBill(uctb.getId(), "one-time-bill");
        Assert.assertNull(billServiceFactory.readBill(uctb.getId(), "one-time-bill"));
    }

    private OneTimeBill createOneTimeBill(){
        return new OneTimeBill(
                "OneTimeBillTest",
                "One Time Bill Test",
                new BigDecimal("123.45"),
                new Date()
        );
    }

    private OneTimeBill updateOneTimeBill(OneTimeBill oneTimeBill){
        oneTimeBill.setName("OneTimeBillUpdateTest");
        oneTimeBill.setDescription("One Time Bill Update Test");
        oneTimeBill.setPaymentAmount(new BigDecimal("234.56"));
        oneTimeBill.setPaymentDate(new Date());
        return oneTimeBill;
    }
}
