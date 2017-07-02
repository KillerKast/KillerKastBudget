package com.killerkast.service.implemenations;

import com.killerkast.entities.Bill;
import com.killerkast.entities.YearlyBill;
import com.killerkast.repositories.YearlyBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("yearlyBillService")
public class YearlyBillServiceImpl extends BillServiceImpl{

    private YearlyBillRepository yearlyBillRepo;

    @Autowired
    YearlyBillServiceImpl(YearlyBillRepository yearlyBillRepo){
        this.yearlyBillRepo = yearlyBillRepo;
    }

    @Override
    public Bill createBill(Bill expense) {
        YearlyBill yearlyBill = (YearlyBill)expense;
        yearlyBill = yearlyBillRepo.insert(yearlyBill);
        return yearlyBill;
    }

    @Override
    public List<?> readAllBills() {
        return yearlyBillRepo.findAll();
    }

    @Override
    public Bill readBill(String id) {
        return yearlyBillRepo.findOne(id);
    }

    @Override
    public Bill updateBill(Bill expense) {
        YearlyBill yearlyBill = (YearlyBill)expense;
        yearlyBill = yearlyBillRepo.save(yearlyBill);
        return yearlyBill;
    }

    @Override
    public void deleteBill(String id) {
        yearlyBillRepo.delete(id);
    }
}
