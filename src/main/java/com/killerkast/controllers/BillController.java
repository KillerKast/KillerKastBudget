package com.killerkast.controllers;

import com.killerkast.entities.Bill;
import com.killerkast.service.interfaces.BillServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin(methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RequestMapping("/api/bill")//api/bill/create/interest-baring-debt
public class BillController {

    private BillServiceFactory billServiceFactory;

    @Autowired
    BillController(BillServiceFactory billServiceFactory){
        this.billServiceFactory = billServiceFactory;
    }


    @RequestMapping(path="/test", method=RequestMethod.GET)
    public void test(){
        System.out.println("test");
    }

    @RequestMapping(path="/create/{billType}", method= RequestMethod.POST)
    public Bill createBill(@RequestBody Bill expense, @PathVariable String billType, HttpServletResponse response) {
        Bill savedBill = billServiceFactory.createBill(expense, billType);
        if (expense == null) response.setStatus(500); else response.setStatus(201);
        return savedBill;
    }

    @RequestMapping(path="/readAll/{billType}", method=RequestMethod.GET)
    public List readAllBills(@PathVariable String billType){
        return billServiceFactory.readAllBills(billType);
    }

    @RequestMapping(path="/read/{billType}/{id}", method=RequestMethod.GET)
    public Bill readBill(@PathVariable String billType, @PathVariable String id){
        return billServiceFactory.readBill(id, billType);
    }

    @RequestMapping(path="/update/{billType}", method=RequestMethod.PUT)
    public Bill updateBill(@RequestBody Bill expense, @PathVariable String billType){
        return billServiceFactory.updateBill(expense, billType);
    }

    @RequestMapping(path="/delete/{billType}/{id}", method=RequestMethod.DELETE)
    public void deleteBill(@PathVariable String id, @PathVariable String billType){
        billServiceFactory.deleteBill(id, billType);
    }
}
