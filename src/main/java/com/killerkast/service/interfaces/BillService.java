package com.killerkast.service.interfaces;

import com.killerkast.entities.Bill;


import java.util.List;

/**
 * Created by killerkast on 6/27/17.
 */
public interface BillService {
    Bill createBill(Bill bill);

    List<?> readAllBills();

    Bill readBill(String id);

    Bill updateBill(Bill bill);

    void deleteBill(String id);

}
