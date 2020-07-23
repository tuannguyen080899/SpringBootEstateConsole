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

import com.laptrinhjavaweb.api.output.BuildingOutput;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;


@RestController
public class BuildingAPI{
	@Autowired
	private IBuildingService buildingService;
	@Autowired
	private IUserService userService;
	
	@GetMapping(value ="/api-building/customer")
	public BuildingOutput getAllBuilding(@RequestParam Map<String,String> model){
		BuildingOutput buildingOutput = new BuildingOutput();
		int page =Integer.parseInt(model.get("page"));
		int limit = Integer.parseInt(model.get("limit"));
		String property = model.get("sortname").toString();
		String order = model.get("sortby").toString();
		buildingOutput.setPage(page);
		buildingOutput.setLimit(limit);
		Pageable pageable = null;
		if(order.equals("ASC")) {
			 pageable = PageRequest.of(page-1, limit,Sort.by(property).ascending());
		}else if(order.equals("DESC")) {
			 pageable = PageRequest.of(page-1, limit,Sort.by(property).descending());
		}
		buildingOutput.setListResults(buildingService.findAllCustom(pageable,property,order)); 
		buildingOutput.setTotalItem(buildingService.totalItem());
		buildingOutput.setTotalPage((int) Math.ceil((double) buildingService.totalItem()/limit));
		return buildingOutput;
	}
	@GetMapping(value ="/api-building")
	public BuildingOutput getBuildingByRole(@RequestParam Map<String,String> model){
		BuildingOutput buildingOutput = new BuildingOutput();
		int page =Integer.parseInt(model.get("page"));
		int limit = Integer.parseInt(model.get("limit"));
		String role = model.get("role");
		Long staffid = Long.parseLong(model.get("staffid"));
		buildingOutput.setPage(page);
		buildingOutput.setLimit(limit);
		Pageable pageable =PageRequest.of(page, limit);
		buildingOutput.setListResults(buildingService.findAll(pageable, role, staffid));
		buildingOutput.setTotalItem(buildingService.totalItem());
		buildingOutput.setTotalPage((int) Math.ceil((double) buildingService.totalItem()/limit));
		return buildingOutput;
	}
	
	@GetMapping(value ="/api-building/search")
	//Request Param lấy tham số của request trên URL
	//Ở đây param truyền vào Map đồng bộ là String hết
	public List<BuildingDTO> searchBuilding(@RequestParam Map<String,String> model, @RequestParam("types") String[]types){
		BuildingSearchBuilder builder=new BuildingSearchBuilder.Builder()
				.setName(model.get("name"))
				.setDistrict(model.get("district"))
				.setFloorArea(model.get("floorArea"))
				.setWard(model.get("ward"))
				.setStreet(model.get("street"))
				.setNumberOfBasement(model.get("numberOfBasement"))
				.setDirection(model.get("direction"))
				.setLevel(model.get("level"))
				.setRentAreaFrom(model.get("rentAreaFrom"))
				.setRentAreaTo(model.get("rentAreaTo"))
				.setRentCostFrom(model.get("rentCostFrom"))
				.setRentAreaTo(model.get("rentCostTo"))
				.setManagerName(model.get("managerName"))
				.setManagerPhone(model.get("managerPhone"))
				.setStaffId(Long.parseLong(model.get("staffId").toString()))
				.setTypes(types)
				.build();
		Pageable pageable = PageRequest.of(Integer.parseInt(model.get("page"))-1, Integer.parseInt(model.get("limit")));
		List<BuildingDTO> result=buildingService.search(builder,pageable);
		return result;
	}
	
	@GetMapping(value ="/api-building/types")
	public Map<String,String> getBuildingType(){
		Map<String,String> buildingTypes = buildingService.getBuildingType();
		return buildingTypes;
	}
	@GetMapping(value="/api-building/{buildingid}/staffs")
	public List<UserDTO> getStaffByBuilding(@PathVariable("buildingid") long buildingid){
		return userService.getAssignmentBuilding(buildingid);
	}
	
	@PostMapping(value ="/api-building")
	public BuildingDTO insertBuilding(@RequestBody BuildingDTO buildingDTO){
		return buildingService.save(buildingDTO);
	}
	
	@PutMapping(value ="/api-building/{buildingid}/staffs/assigmentbuilding")
	public List<UserDTO> assigmentBuilding(@PathVariable("buildingid") Long buildingid,@RequestBody Long [] staffIds){ 
		userService.saveAssignmentBuilding(buildingid, staffIds);
		return userService.getAssignmentBuilding(buildingid);
	}
	
	@PutMapping(value ="/api-building/{buildingid}")
	public BuildingDTO saveBuilding(@PathVariable("buildingid") Long buildingid,@RequestBody BuildingDTO buildingDTO){ 
		buildingDTO.setId(buildingid);
		return buildingService.save(buildingDTO);
	}
	
	@DeleteMapping(value ="/api-building")
	public void deleteBuilding(@RequestBody long [] ids){
		buildingService.delete(ids);
	}
	
	@GetMapping(value ="/api-building/{buildingid}")
	public BuildingDTO findById(@PathVariable("buildingid") Long buildingid){
		return buildingService.findById(buildingid);
	}
}
