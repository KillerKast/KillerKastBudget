package com.killerkast.service.implemenations;

import com.killerkast.entities.Bill;
import com.killerkast.entities.MonthlyBill;
import com.killerkast.repositories.MonthlyBillRepository;
import com.killerkast.service.interfaces.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by killerkast on 6/27/17.
 */
@Component("monthlyBillService")
public class MonthlyBillServiceImpl extends BillServiceImpl {

    private MonthlyBillRepository monthlyBillRepo;

    @Autowired
    MonthlyBillServiceImpl(MonthlyBillRepository monthlyBillRepo){
        this.monthlyBillRepo = monthlyBillRepo;
    }

    @Override
    public Bill createBill(Bill bill) {
        MonthlyBill monthlyBill = (MonthlyBill)bill;
        monthlyBill = monthlyBillRepo.insert(monthlyBill);
        return monthlyBill;
    }

    @Override
    public List<?> readAllBills() {
        return monthlyBillRepo.findAll();
    }

    @Override
    public Bill readBill(String id) {
        return monthlyBillRepo.findOne(id);
    }

    @Override
    public Bill updateBill(Bill bill) {
        MonthlyBill monthlyBill = (MonthlyBill)bill;
        monthlyBill = monthlyBillRepo.save(monthlyBill);
        return monthlyBill;
    }

    @Override
    public void deleteBill(String id) {
        monthlyBillRepo.delete(id);
    }

}
