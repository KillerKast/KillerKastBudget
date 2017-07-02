package com.killerkast.services;

import com.killerkast.entities.InterestBaringDebt;
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
 */@RunWith(SpringRunner.class)
@SpringBootTest
public class InterestBaringDebtTest {
    @Autowired
    BillServiceFactory billServiceFactory;

    @Test
    public void InterestBaringDebtTest(){
        /** Test Create **/
        InterestBaringDebt cibd = (InterestBaringDebt)billServiceFactory.createBill(createInterestBaringDebt(), "interest-baring-debt");
        InterestBaringDebt rcibd = (InterestBaringDebt)billServiceFactory.readBill(cibd.getId(), "interest-baring-debt");
        Assert.assertTrue(cibd.equals(rcibd));

        /** Test Update **/
        InterestBaringDebt uibd = (InterestBaringDebt)billServiceFactory.updateBill(updateInterstBaringDebt(rcibd), "interest-baring-debt");
        InterestBaringDebt uribd = (InterestBaringDebt)billServiceFactory.readBill(uibd.getId(), "interest-baring-debt");
        Assert.assertTrue(uibd.equals(uribd));

        /**Test Delete**/
        billServiceFactory.deleteBill(uribd.getId(), "interest-baring-debt");
        Assert.assertNull(billServiceFactory.readBill(uribd.getId(), "interest-baring-debt"));
    }

    private InterestBaringDebt createInterestBaringDebt(){
        return new InterestBaringDebt(
                "InterestBaringDebtTest",
                "Interest Baring Debt Test",
                new BigDecimal("123.45"),
                12,
                new BigDecimal("345.00"),
                new BigDecimal("3.00")
        );
    }

    private InterestBaringDebt updateInterstBaringDebt(InterestBaringDebt interestBaringDebt){
        interestBaringDebt.setName("InterestBaringDebtUpdateTest");
        interestBaringDebt.setDescription("Interest Baring Debt Update Test");
        interestBaringDebt.setPaymentAmount(new BigDecimal("234.56"));
        interestBaringDebt.setPaymentDate(23);
        interestBaringDebt.setStartingBalance(new BigDecimal("456.00"));
        interestBaringDebt.setApr(new BigDecimal("4.00"));
        return interestBaringDebt;
    }
}
