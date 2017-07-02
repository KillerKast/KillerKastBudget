package com.killerkast.controllers;

import com.killerkast.controllers.BillController;
import com.killerkast.entities.MonthlyBill;
import com.killerkast.service.interfaces.BillServiceFactory;
import com.mongodb.util.JSON;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
/**
 * Created by killerkast on 6/30/17.
 */
public class BillControllerTest {

    @InjectMocks
    BillController billController;

    @Mock
    BillServiceFactory billServiceFactory;


    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(billController)
                .build();
    }


    @Test
    public void bill_controller_create() throws Exception{
        MonthlyBill mb = new MonthlyBill("MonthlyBillTest", "Monthly Bill Test", new BigDecimal("123.45"), 12);
        JSONObject jo = new JSONObject();

        jo.put("name", mb.getName());
        jo.put("description",mb.getDescription());
        jo.put("paymentAmount", mb.getPaymentAmount());
        jo.put("paymentDate", mb.getPaymentDate());
        jo.put("type", "monthly-bill");

        mockMvc.perform(post("/api/bill/create/monthly-bill")
            .contentType(MediaType.APPLICATION_JSON).content(jo.toString()))
            .andExpect(status().isCreated());
    }

    @Test
    public void bill_controller_get() throws Exception {
        mockMvc.perform(get("/api/bill/read/MonthlyBill/test"))
                .andExpect(status().isOk());
    }

    @Test
    public void bill_controller_update() throws Exception {
        MonthlyBill mb = new MonthlyBill("MonthlyBillTest", "Monthly Bill Test", new BigDecimal("123.45"), 12);
        JSONObject jo = new JSONObject();

        jo.put("name", mb.getName());
        jo.put("description",mb.getDescription());
        jo.put("paymentAmount", mb.getPaymentAmount());
        jo.put("paymentDate", mb.getPaymentDate());
        jo.put("type", "monthly-bill");

        mockMvc.perform(put("/api/bill/update/{billType}", "MonthlyBill")
                .contentType(MediaType.APPLICATION_JSON).content(jo.toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void bill_controller_delete() throws Exception {
        mockMvc.perform(delete("/api/bill/delete/{billType}/{id}", "MonthlyBill", "TestID"))
            .andExpect(status().isOk());
    }
}
