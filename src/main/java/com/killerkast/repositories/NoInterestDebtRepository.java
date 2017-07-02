package com.killerkast.repositories;

import com.killerkast.entities.NoInterestDebt;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoInterestDebtRepository extends MongoRepository<NoInterestDebt, String>{
}