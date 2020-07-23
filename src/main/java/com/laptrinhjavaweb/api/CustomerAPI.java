package com.laptrinhjavaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.api.output.CustomerOutput;
import com.laptrinhjavaweb.api.output.DealOutput;
import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.DealDTO;
import com.laptrinhjavaweb.service.ICustomerService;

@RestController
public class CustomerAPI{
	
	@Autowired
	private ICustomerService customerService;
	
	@GetMapping(value="api-customer")
	public CustomerOutput getCustomer (@RequestParam Map<String,String> model){
		CustomerOutput customerOutput = new CustomerOutput();
		int page =Integer.parseInt(model.get("page"));
		int limit = Integer.parseInt(model.get("limit"));
		String property = model.get("sortname").toString();
		String order = model.get("sortby").toString();
		customerOutput.setPage(page);
		customerOutput.setLimit(limit);
		Pageable pageable = null;
		if(order.equals("ASC")) {
			 pageable = PageRequest.of(page-1, limit,Sort.by(property).ascending());
		}else if(order.equals("DESC")) {
			 pageable = PageRequest.of(page-1, limit,Sort.by(property).descending());
		}
		List<CustomerDTO> result = customerService.findAll( pageable);
		customerOutput.setListResults(result);
		customerOutput.setTotalItem(customerService.totalItem());
		customerOutput.setTotalPage((int) Math.ceil((double) customerService.totalItem()/limit));
		return customerOutput;
	}
	@GetMapping(value="api-customer/search")
	public CustomerOutput searchCustomer (@RequestParam Map<String,String> model){
		CustomerSearchBuilder builder=new CustomerSearchBuilder.Builder()
				.setName(model.get("name"))
				.setPhone(model.get("phone"))
				.setEmail(model.get("email"))
				.setStaffId(Long.parseLong(model.get("staffid").toString()))
				.build();
		CustomerOutput customerOutput = new CustomerOutput();
		int page =Integer.parseInt(model.get("page"));
		int limit = Integer.parseInt(model.get("limit"));
		customerOutput.setPage(page);
		customerOutput.setLimit(limit);
		Pageable pageable = PageRequest.of(page, limit);
		List<CustomerDTO> result = customerService.search(builder, pageable);
		customerOutput.setListResults(result);
		customerOutput.setTotalItem(customerService.totalItem());
		customerOutput.setTotalPage((int) Math.ceil((double) customerService.totalItem()/limit));
		return customerOutput;
	}
	@GetMapping(value="api-customer/{customerid}")
	public CustomerDTO getCustomerById(@PathVariable("customerid") long customerid ){
		return customerService.findById(customerid);
	}
	@GetMapping(value="api-customer/{customerid}/deals")
	public DealOutput getDeals (@RequestParam Map<String,String> model){
		int page =Integer.parseInt(model.get("page"));
		int limit = Integer.parseInt(model.get("limit"));
		Pageable pageable = PageRequest.of(page,limit);
		DealOutput dealOutput = customerService.getDeals(Long.parseLong(model.get("customerid")), pageable);
		return dealOutput;
	}
	@PostMapping(value="/api-customer")
	public CustomerDTO insertCustomer(@RequestBody CustomerDTO customerDTO){
		return customerService.save(customerDTO);
	}
	
	@PostMapping(value="/api-customer/deals")
	public void insertDeals(@RequestBody List<DealDTO>customerService,List<DealDTO> leadToVisit){
		  ((ICustomerService) customerService).saveDeals(customerService,leadToVisit);
	}
	
	@PutMapping(value ="/api-customer/{customerid}")
	public CustomerDTO updateCustomer(@PathVariable("customerid") Long customerid,@RequestBody CustomerDTO customerDTO){ 
		customerDTO.setId(customerid);
		return customerService.save(customerDTO);
	}
	
	@PutMapping(value ="/api-customer/{customerid}/deals")
	public void updateDeals(@PathVariable("customerid") Long customerid,@RequestBody List<DealDTO>customerService,List<DealDTO> leadToVisit){ 
		for(DealDTO deal:customerService) {
			deal.setCustomerId(customerid);
		}
		for(DealDTO deal:leadToVisit) {
			deal.setCustomerId(customerid);
		}
		((ICustomerService) customerService).saveDeals(customerService,leadToVisit);
	}
	
	@DeleteMapping(value ="/api-customer")
	public void deleteCustomer(@RequestBody Long[] ids){
		customerService.delete(ids);
	}
	
	@DeleteMapping(value ="/api-customer/deals")
	public void deleteDeals(@RequestBody Long[] ids){
		customerService.deleteDeals(ids);
	}
}
