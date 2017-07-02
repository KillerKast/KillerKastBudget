package com.killerkast.service.implemenations;

import com.killerkast.entities.Bill;
import com.killerkast.entities.InterestBaringDebt;
import com.killerkast.repositories.InterestBaringDebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("interestBaringDebtService")
public class InterestBaringDebtServiceImpl extends BillServiceImpl {

    private InterestBaringDebtRepository ibdRepo;

    @Autowired
    InterestBaringDebtServiceImpl(InterestBaringDebtRepository ibdRepo){
        this.ibdRepo = ibdRepo;
    }

    @Override
    public Bill createBill(Bill bill) {
        InterestBaringDebt ibd = (InterestBaringDebt)bill;
        ibd = ibdRepo.insert(ibd);
        return ibd;
    }

    @Override
    public List<?> readAllBills() {
        return ibdRepo.findAll();
    }

    @Override
    public Bill readBill(String id) {
        return ibdRepo.findOne(id);
    }

    @Override
    public Bill updateBill(Bill bill) {
        InterestBaringDebt ibd = (InterestBaringDebt)bill;
        ibd = ibdRepo.save(ibd);
        return ibd;
    }

    @Override
    public void deleteBill(String id) {
        ibdRepo.delete(id);
    }
}
