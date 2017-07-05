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
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Component("interestBaringDebtService")
public class InterestBaringDebtServiceImpl extends BillServiceImpl {

    private InterestBaringDebtRepository ibdRepo;
    private InterestBaringPaymentScheduleRepository ibpsRepo;
    private Logger logger =
            LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

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
        calculateInterestBaringPaymentSchedule(ibd);
        return ibd;
    }

    private void calculateInterestBaringPaymentSchedule(InterestBaringDebt ibd) {
        BigDecimal currentOwed = ibd.getStartingBalance();
        BigDecimal lastSavedAmount = ibd.getStartingBalance();
        BigDecimal dailyInterestRate = ibd.getApr();
        dailyInterestRate = dailyInterestRate
                .divide(new BigDecimal(100.00), 25, BigDecimal.ROUND_DOWN)
                .divide(new BigDecimal(365.0), 25, BigDecimal.ROUND_DOWN);
        BigDecimal dailyInterestMultiplier = dailyInterestRate.add(new BigDecimal(1.00));
        LocalDate currentDate = LocalDate.now();

        while(currentOwed.doubleValue() > 0.00){
            if(currentDate.getDayOfMonth() == ibd.getPaymentDate()){
                InterestBaringPaymentSchedule ibps =
                        new InterestBaringPaymentSchedule(
                                currentDate,
                                currentOwed.setScale(2, BigDecimal.ROUND_CEILING),
                                currentOwed.subtract(lastSavedAmount).setScale(2, BigDecimal.ROUND_CEILING),
                                ibd);
                ibpsRepo.insert(ibps);
                String logString = String.format("%s - %s, %s",
                        ibps.getDate(),
                        ibps.getOwed(),
                        ibps.getInterestOwed());
                System.out.println(logString);
                currentOwed = currentOwed.subtract(ibd.getPaymentAmount());
                lastSavedAmount = currentOwed;
            }
            currentDate = currentDate.plusDays(1);
            currentOwed = currentOwed.multiply(dailyInterestMultiplier);
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
