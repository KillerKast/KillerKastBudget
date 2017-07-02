package com.killerkast.services;

import com.killerkast.entities.NoInterestDebt;
import com.killerkast.service.interfaces.BillServiceFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Created by killerkast on 7/1/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NoInterestDebtTest {
    @Autowired
    BillServiceFactory billServiceFactory;

    @Test
    public void NoInterestDebtTest(){
        /**Test Create**/
        NoInterestDebt cnid = (NoInterestDebt)billServiceFactory.createBill(createNoInterestDebt(), "no-interest-debt");
        NoInterestDebt rnid = (NoInterestDebt)billServiceFactory.readBill(cnid.getId(), "no-interest-debt");
        Assert.assertTrue(cnid.equals(rnid));
        /**Test Update**/
        NoInterestDebt unid = (NoInterestDebt)billServiceFactory.updateBill(updateNoInterestDate(rnid),"no-interest-debt");
        NoInterestDebt runid = (NoInterestDebt)billServiceFactory.readBill(unid.getId(), "no-interest-debt");
        Assert.assertTrue(unid.equals(runid));
        /**Test Delete**/
        billServiceFactory.deleteBill(runid.getId(), "no-interest-debt");
        Assert.assertNull(billServiceFactory.readBill(runid.getId(), "no-interest-debt"));
    }

    private NoInterestDebt createNoInterestDebt(){
        return new NoInterestDebt(
                "NoInterestDebtTest",
                "No Interest Debt Test",
                new BigDecimal("123.45"),
                12,
                new BigDecimal("345.54")
        );
    }

    private NoInterestDebt updateNoInterestDate(NoInterestDebt noInterestDebt){
        noInterestDebt.setName("NoInterestDebtUpdateTest");
        noInterestDebt.setDescription("No Interest Debt Update Test");
        noInterestDebt.setPaymentAmount(new BigDecimal("234.56"));
        noInterestDebt.setStartingBalance(new BigDecimal("678.90"));
        noInterestDebt.setPaymentDate(13);
        return noInterestDebt;
    }
}
