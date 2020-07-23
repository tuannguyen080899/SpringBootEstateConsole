package com.laptrinhjavaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>,CustomerRepositoryCustom{

}
