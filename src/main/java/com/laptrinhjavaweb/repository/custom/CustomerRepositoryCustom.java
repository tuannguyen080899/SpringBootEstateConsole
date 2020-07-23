package com.laptrinhjavaweb.repository.custom;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.DealEntity;

public interface CustomerRepositoryCustom {
	List<CustomerEntity> findAll(Map<String,Object> params,CustomerSearchBuilder builder,Pageable pageable);
	List<DealEntity> findDealsByCustomer(Long customerId,Pageable pageable);
}

