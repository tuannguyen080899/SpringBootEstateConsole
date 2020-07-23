package com.laptrinhjavaweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.api.output.DealOutput;
import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.DealDTO;

public interface ICustomerService {
	List<CustomerDTO> findAll(Pageable pageable);
	List<CustomerDTO> search(CustomerSearchBuilder builder,Pageable pageable);
	DealOutput getDeals(Long customerId,Pageable pageable);
	void saveDeals(List<DealDTO> customerService,List<DealDTO> leadToVisit);
	void deleteDeals(Long[]dealIds);
	CustomerDTO findById(Long customerId);
	CustomerDTO save(CustomerDTO userDTO);
	void delete(Long[]ids);
	int totalItem();
}
