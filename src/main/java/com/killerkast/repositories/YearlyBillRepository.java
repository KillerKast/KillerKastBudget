package com.killerkast.repositories;

import com.killerkast.entities.YearlyBill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface YearlyBillRepository extends MongoRepository<YearlyBill, String> {
}
