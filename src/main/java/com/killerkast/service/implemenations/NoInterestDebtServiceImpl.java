package com.killerkast.service.implemenations;

import com.killerkast.entities.Bill;
import com.killerkast.entities.NoInterestDebt;
import com.killerkast.repositories.NoInterestDebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component("noInterestDebtService")
public class NoInterestDebtServiceImpl extends BillServiceImpl {

    private NoInterestDebtRepository nidRepo;

    @Autowired
    NoInterestDebtServiceImpl( NoInterestDebtRepository nidRepo){
        this.nidRepo = nidRepo;
    }

    @Override
    public Bill createBill(Bill bill) {
        NoInterestDebt nid = (NoInterestDebt)bill;
        nid = nidRepo.insert(nid);
        return nid;
    }

    @Override
    public List<?> readAllBills() {
        return nidRepo.findAll();
    }

    @Override
    public Bill readBill(String id) {
        return nidRepo.findOne(id);
    }

    @Override
    public Bill updateBill(Bill bill) {
        NoInterestDebt nid = (NoInterestDebt)bill;
        nid = nidRepo.save(nid);
        return nid;
    }

    @Override
    public void deleteBill(String id) {
        nidRepo.delete(id);
    }
}
