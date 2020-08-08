package com.cognizant.membership.repository;

import com.cognizant.membership.model.Plan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PlanRepository extends CrudRepository<Plan, Integer> {

    List<Plan> findByPlanid(Integer planid);

}
