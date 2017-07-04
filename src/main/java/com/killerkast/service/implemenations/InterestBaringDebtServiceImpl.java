package com.killerkast.service.implemenations;

import com.killerkast.entities.Bill;
import com.killerkast.entities.InterestBaringDebt;
import com.killerkast.entities.InterestBaringPaymentSchedule;
import com.killerkast.repositories.InterestBaringDebtRepository;
import com.killerkast.repositories.InterestBaringPaymentScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Component("interestBaringDebtService")
public class InterestBaringDebtServiceImpl extends BillServiceImpl {

    private InterestBaringDebtRepository ibdRepo;
    private InterestBaringPaymentScheduleRepository ibpsRepo;
    private Logger logger =
            LoggerFactory.getLogger(InterestBaringDebtServiceImpl.class);

    @Autowired
    InterestBaringDebtServiceImpl(
            InterestBaringDebtRepository ibdRepo,
            InterestBaringPaymentScheduleRepository ibpsRepo
    ){
        this.ibdRepo = ibdRepo;
        this.ibpsRepo = ibpsRepo;
    }

    @Override
    public Bill createBill(Bill bill) {
        logger.trace("createBill from InterestBaringDebtServiceImpl");
        InterestBaringDebt ibd = (InterestBaringDebt)bill;
        ibd = ibdRepo.insert(ibd);
        calculateInterestBaingPaymentSchedule(ibd);
        return ibd;
    }

    private void calculateInterestBaingPaymentSchedule(InterestBaringDebt ibd) {
        BigDecimal currentOwed = ibd.getStartingBalance();
        BigDecimal lastSavedAmount = ibd.getStartingBalance();
        BigDecimal dailyInterestRate = ibd.getApr().divide(new BigDecimal(100));
        Calendar currentDate = Calendar.getInstance();
        logger.debug("Current Owed: " + currentOwed);
        System.out.print("Last Saved Amount: ");
        System.out.println(lastSavedAmount);
        System.out.print("Daily Interest Rate: ");
        System.out.println(dailyInterestRate);
        System.out.print("Current Date: ");
        System.out.println(currentDate);
        while(currentOwed.doubleValue() > 0.00){
            if(currentDate.get(Calendar.DAY_OF_MONTH) == ibd.getPaymentDate()){
                InterestBaringPaymentSchedule ibps =
                    new InterestBaringPaymentSchedule(currentDate, currentOwed, (currentOwed.subtract(lastSavedAmount)), ibd);
                ibpsRepo.insert(ibps);
                System.out.println(String.format("IPBS: {" +
                        "date:" + ibps.getDate().toString() +
                        ", currentOwed" + ibps.getOwed())
                        + "}");
                currentOwed = currentOwed.subtract(ibd.getPaymentAmount());
                lastSavedAmount = currentOwed;
            }
            currentDate.add(Calendar.DATE, 1);
            currentOwed = currentOwed.multiply(new BigDecimal(1.00).add(dailyInterestRate));
        }
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
