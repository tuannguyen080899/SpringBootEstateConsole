package com.laptrinhjavaweb.api.output;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dto.CustomerDTO;

public class CustomerOutput extends BaseOutput{
private List<CustomerDTO> listResults = new ArrayList<CustomerDTO>();
	
	
	public List<CustomerDTO> getListResults() {
		return listResults;
	}
	public void setListResults(List<CustomerDTO> listResults) {
		this.listResults = listResults;
	}
	
}
