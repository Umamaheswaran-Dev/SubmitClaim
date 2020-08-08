package com.cognizant.membership.repository;

import com.cognizant.membership.model.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AddressRepository extends CrudRepository<Address, Integer> {

    List<Address> findByAddressid(Integer addressid);

}
