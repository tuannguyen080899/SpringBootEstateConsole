package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
@Component
public class CustomerConverter {
	@Autowired
	private ModelMapper modelMapper;
		
		public CustomerDTO convertEntityToDTO(CustomerEntity entity) {
			CustomerDTO userDTO=modelMapper.map(entity, CustomerDTO.class);
			return userDTO;
		}
		public CustomerEntity convertDTOToEntity(CustomerDTO dto) {
			CustomerEntity userEntity=modelMapper.map(dto, CustomerEntity.class);
			return userEntity;
		}
}
