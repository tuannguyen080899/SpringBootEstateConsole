package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;

public interface IBuildingService {
	List<BuildingDTO> findAllCustom(Pageable pageable,String property,String order);
	List<BuildingDTO> findAll(Pageable pagebale,String role,Long staffid);
	List<BuildingDTO> search(BuildingSearchBuilder builder,Pageable pageable);
	Map<String,String> getBuildingType();
	BuildingDTO findById(Long id);
	BuildingDTO save(BuildingDTO dto) ;
	void delete(long [] ids) ;
	int totalItem();
}
