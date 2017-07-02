package com.killerkast.service.interfaces;

import com.killerkast.entities.Bill;
import java.util.List;

public interface BillServiceFactory {
    Bill createBill(Bill bill, String billType);
    List<?> readAllBills(String billType);
    Bill readBill(String id, String billType);
    Bill updateBill(Bill bill, String billType);
    void deleteBill(String id, String billType);

}
