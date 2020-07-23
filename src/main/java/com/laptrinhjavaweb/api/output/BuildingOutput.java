package com.laptrinhjavaweb.api.output;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dto.BuildingDTO;

public class BuildingOutput extends BaseOutput{
	
	
	private List<BuildingDTO> listResults = new ArrayList<BuildingDTO>();
	
	
	public List<BuildingDTO> getListResults() {
		return listResults;
	}
	public void setListResults(List<BuildingDTO> listResults) {
		this.listResults = listResults;
	}
}
