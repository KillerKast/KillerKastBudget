package com.killerkast.services;

import com.killerkast.entities.PaymentPlan;
import com.killerkast.service.interfaces.BillServiceFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by killerkast on 7/1/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentPlanTest {
    @Autowired
    BillServiceFactory billServiceFactory;

    @Test
    public void PaymentPlanDebtTest() {
        /**Test Create **/
        PaymentPlan cppd = (PaymentPlan)billServiceFactory.createBill(createPaymentPlan(), "payment-plan");
        PaymentPlan rcppd = (PaymentPlan)billServiceFactory.readBill(cppd.getId(), "payment-plan");
        Assert.assertTrue(rcppd.equals(cppd));

        /**Test Update**/
        PaymentPlan uppd  = (PaymentPlan)billServiceFactory.updateBill(updatePaymentPlan(rcppd), "payment-plan");
        PaymentPlan ruppd = (PaymentPlan)billServiceFactory.readBill(uppd.getId(), "payment-plan");
        Assert.assertTrue(ruppd.equals(uppd));

        /**Test Delete**/
        billServiceFactory.deleteBill(ruppd.getId(), "payment-plan");
        Assert.assertNull(billServiceFactory.readBill(ruppd.getId(), "payment-plan"));
    }

    private PaymentPlan createPaymentPlan(){
        return new PaymentPlan(
                "PaymentPlanDebtTest",
                "Payment Plan Debt Test",
                new BigDecimal("123.45"),
                12,
                new BigDecimal("345.00"),
                new Date()
        );
    }

    private PaymentPlan updatePaymentPlan(PaymentPlan paymentPlan){
        paymentPlan.setName("PaymentPlanDebtUpdateTest");
        paymentPlan.setDescription("Payment Plan Debt Update Test");
        paymentPlan.setPaymentAmount(new BigDecimal("234.56"));
        paymentPlan.setPaymentDate(23);
        paymentPlan.setStartingBalance(new BigDecimal("456.00"));
        paymentPlan.setLastPaymentDate(new Date());
        return paymentPlan;
    }
}
