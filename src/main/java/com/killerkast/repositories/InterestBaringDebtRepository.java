package com.killerkast.repositories;

import com.killerkast.entities.InterestBaringDebt;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InterestBaringDebtRepository extends MongoRepository<InterestBaringDebt, String>{
}