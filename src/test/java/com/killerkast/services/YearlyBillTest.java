package com.killerkast.services;

import com.killerkast.entities.YearlyBill;
import com.killerkast.service.interfaces.BillServiceFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.Year;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YearlyBillTest {
    @Autowired
    BillServiceFactory billServiceFactory;

    @Test
    public void TestYearlyBill(){
        /**Test Create**/
        YearlyBill cyb = (YearlyBill)billServiceFactory.createBill(createYearlyBill(), "yearly-bill");
        YearlyBill rcyb = (YearlyBill)billServiceFactory.readBill(cyb.getId(), "yearly-bill");
        Assert.assertTrue(cyb.equals(rcyb));

        /**Test Update**/
        YearlyBill uyb = (YearlyBill)billServiceFactory.updateBill(updateYearlyBill(rcyb), "yearly-bill");
        YearlyBill ruyb = (YearlyBill)billServiceFactory.readBill(uyb.getId(), "yearly-bill");
        Assert.assertTrue(uyb.equals(ruyb));

        /**Test Delete**/
        billServiceFactory.deleteBill(ruyb.getId(), "yearly-bill");
        Assert.assertNull(billServiceFactory.readBill(ruyb.getId(), "yearly-bill"));
    }

    private YearlyBill createYearlyBill(){
        return new YearlyBill(
            "YearlyBillTest",
            "Yearly Bill Test",
            new BigDecimal("123.45"),
            12,
            24
        );
    }

    private YearlyBill updateYearlyBill(YearlyBill yearlyBill){
        yearlyBill.setName("YearlyBillUpdateTest");
        yearlyBill.setDescription("Yearly Bill Update Test");
        yearlyBill.setPaymentAmount(new BigDecimal("234.56"));
        yearlyBill.setPaymentMonth(11);
        yearlyBill.setPaymentDay(23);
        return yearlyBill;
    }
}
