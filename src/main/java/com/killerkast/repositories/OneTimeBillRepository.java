package com.killerkast.repositories;

import com.killerkast.entities.OneTimeBill;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by killerkast on 6/27/17.
 */
public interface OneTimeBillRepository extends MongoRepository<OneTimeBill, String> {
}
