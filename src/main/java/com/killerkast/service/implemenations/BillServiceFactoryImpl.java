package com.killerkast.service.implemenations;

import com.killerkast.entities.Bill;
import com.killerkast.service.interfaces.BillService;
import com.killerkast.service.interfaces.BillServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("billServiceFactory")
public class BillServiceFactoryImpl implements BillServiceFactory {

    private BillService billService;
    private BillService monthlyBillService;
    private BillService oneTimeBillService;
    private BillService yearlyBillService;
    private BillService noInterestDebtService;
    private BillService interestBaringDebtService;
    private BillService paymentPlanDebtService;

    @Autowired
    BillServiceFactoryImpl(
            @Qualifier("monthlyBillService") BillService billService,
            @Qualifier("monthlyBillService") BillService monthlyBillService,
            @Qualifier("oneTimeBillService") BillService oneTimeBillService,
            @Qualifier("yearlyBillService") BillService yearlyBillService,
            @Qualifier("noInterestDebtService") BillService noInterestDebtService,
            @Qualifier("interestBaringDebtService") BillService interestBaringDebtService,
            @Qualifier("paymentPlanDebtService") BillService paymentPlanDebtService
    ){
        this.billService = billService;
        this.monthlyBillService = monthlyBillService;
        this.oneTimeBillService = oneTimeBillService;
        this.yearlyBillService = yearlyBillService;
        this.noInterestDebtService = noInterestDebtService;
        this.interestBaringDebtService = interestBaringDebtService;
        this.paymentPlanDebtService = paymentPlanDebtService;
    }


    @Override
    public Bill createBill(Bill bill, String billType) {
        getBillService(billType);
        return billService.createBill(bill);
    }

    @Override
    public List<?> readAllBills(String billType) {
        getBillService(billType);
        return billService.readAllBills();
    }

    @Override
    public Bill readBill(String id, String billType) {
        getBillService(billType);
        return billService.readBill(id);
    }

    @Override
    public Bill updateBill(Bill bill, String billType) {
        getBillService(billType);
        return billService.updateBill(bill);
    }

    @Override
    public void deleteBill(String id, String billType) {
        getBillService(billType);
        billService.deleteBill(id);
    }

    private void getBillService(String billType){
        switch(billType){
            case "monthly-bill":
                billService = monthlyBillService;
                break;
            case "yearly-bill":
                billService = yearlyBillService;
                break;
            case "one-time-bill":
                billService = oneTimeBillService;
                break;
            case "no-interest-debt":
                billService = noInterestDebtService;
                break;
            case "interest-baring-debt":
                billService = interestBaringDebtService;
                break;
            case "payment-plan":
                billService = paymentPlanDebtService;
                break;
        }

    }
}
