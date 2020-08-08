package com.cognizant.membership.repository;

import com.cognizant.membership.model.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MemberRepository extends CrudRepository<Member, Integer> {

    boolean existsByUserid(String userid);

    List<Member> findByUserid(String userid);

}
