package com.killerkast.service.implemenations;

import com.killerkast.entities.Bill;
import com.killerkast.entities.PaymentPlan;
import com.killerkast.repositories.PaymentPlanDebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component("paymentPlanDebtService")
public class PaymentPlanDebtServiceImpl extends BillServiceImpl {

    private PaymentPlanDebtRepository ppdRepo;

    @Autowired
    PaymentPlanDebtServiceImpl(PaymentPlanDebtRepository ppdRepo){
        this.ppdRepo = ppdRepo;
    }
    
    @Override
    public Bill createBill(Bill bill) {
        PaymentPlan ppd = (PaymentPlan) bill;
        ppd = ppdRepo.insert(ppd);
        return ppd;
    }

    @Override
    public List<?> readAllBills() {
        return ppdRepo.findAll();
    }

    @Override
    public Bill readBill(String id) {
        return ppdRepo.findOne(id);
    }

    @Override
    public Bill updateBill(Bill bill) {
        PaymentPlan ppd = (PaymentPlan)bill;
        ppd = ppdRepo.save(ppd);
        return ppd;
    }

    @Override
    public void deleteBill(String id) {
        ppdRepo.delete(id);
    }
}
