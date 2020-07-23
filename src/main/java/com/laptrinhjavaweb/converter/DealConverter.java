package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.DealDTO;
import com.laptrinhjavaweb.entity.DealEntity;
@Component
public class DealConverter {
private ModelMapper modelMapper=new ModelMapper();
	
	public DealDTO convertEntityToDTO(DealEntity entity) {
		DealDTO userDTO=modelMapper.map(entity, DealDTO.class);
		return userDTO;
	}
	public DealEntity convertDTOToEntity(DealDTO dto) {
		DealEntity userEntity=modelMapper.map(dto, DealEntity.class);
		return userEntity;
	}
}
