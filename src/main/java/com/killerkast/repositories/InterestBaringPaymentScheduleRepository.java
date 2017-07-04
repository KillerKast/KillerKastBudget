package com.killerkast.repositories;

import com.killerkast.entities.InterestBaringPaymentSchedule;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by killerkast on 7/4/17.
 */
public interface InterestBaringPaymentScheduleRepository extends MongoRepository<InterestBaringPaymentSchedule,String>{
}
