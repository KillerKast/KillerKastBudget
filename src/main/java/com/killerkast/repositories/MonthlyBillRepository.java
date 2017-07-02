package com.killerkast.repositories;

import com.killerkast.entities.MonthlyBill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MonthlyBillRepository extends MongoRepository<MonthlyBill, String> {
}
