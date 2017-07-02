package com.killerkast.service.implemenations;

import com.killerkast.entities.Bill;
import com.killerkast.entities.OneTimeBill;
import com.killerkast.repositories.OneTimeBillRepository;
import com.killerkast.service.interfaces.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("oneTimeBillService")
public class OneTimeBillServiceImpl extends BillServiceImpl {

    private OneTimeBillRepository oneTimeBillRepo;
    
    @Autowired
    OneTimeBillServiceImpl(OneTimeBillRepository oneTimeBillRepo){
        this.oneTimeBillRepo = oneTimeBillRepo;
    }
    
    @Override
    public Bill createBill(Bill bill) {
        OneTimeBill oneTimeBill = (OneTimeBill)bill;
        oneTimeBill = oneTimeBillRepo.insert(oneTimeBill);
        return oneTimeBill;
    }

    @Override
    public List<?> readAllBills() {
        return oneTimeBillRepo.findAll();
    }

    @Override
    public Bill readBill(String id) {
        return oneTimeBillRepo.findOne(id);
    }

    @Override
    public Bill updateBill(Bill bill) {
        OneTimeBill oneTimeBill = (OneTimeBill)bill;
        oneTimeBill = oneTimeBillRepo.save(oneTimeBill);
        return oneTimeBill;
    }

    @Override
    public void deleteBill(String id) {
        oneTimeBillRepo.delete(id);
    }
}

