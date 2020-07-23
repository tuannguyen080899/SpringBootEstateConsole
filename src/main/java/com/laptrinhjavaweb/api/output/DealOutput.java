package com.laptrinhjavaweb.api.output;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.dto.DealDTO;

public class DealOutput {
	private Map<String,String> dealType = new HashMap<String,String>();
	private List<DealDTO> customerService = new ArrayList<DealDTO>();
	private List<DealDTO> leadToVisit = new ArrayList<DealDTO>();
	public Map<String, String> getDealType() {
		return dealType;
	}
	public void setDealType(Map<String, String> dealType) {
		this.dealType = dealType;
	}
	public List<DealDTO> getCustomerService() {
		return customerService;
	}
	public void setCustomerService(List<DealDTO> customerService) {
		this.customerService = customerService;
	}
	public List<DealDTO> getLeadToVisit() {
		return leadToVisit;
	}
	public void setLeadToVisit(List<DealDTO> leadToVisit) {
		this.leadToVisit = leadToVisit;
	}
	
	
}
