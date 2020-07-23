package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;

@Component
public class BuildingConverter {
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingDTO convertEntityToDTO(BuildingEntity entity) {
		BuildingDTO buildingDTO=modelMapper.map(entity, BuildingDTO.class);
		return buildingDTO;
	}
	public BuildingEntity convertDTOToEntity(BuildingDTO dto) {
		BuildingEntity buildingDTO=modelMapper.map(dto, BuildingEntity.class);
		return buildingDTO;
	}
	
}
