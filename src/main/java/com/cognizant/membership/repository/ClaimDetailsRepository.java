package com.cognizant.membership.repository;

import com.cognizant.membership.model.ClaimDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClaimDetailsRepository extends CrudRepository<ClaimDetails, Integer> {

    boolean existsByMemberid(Integer memberid);

    List<ClaimDetails> findByMemberid(Integer memberid);

}
