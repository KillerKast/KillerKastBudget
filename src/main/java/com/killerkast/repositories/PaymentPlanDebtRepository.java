package com.killerkast.repositories;

import com.killerkast.entities.PaymentPlan;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PaymentPlanDebtRepository extends MongoRepository<PaymentPlan, String>{
}