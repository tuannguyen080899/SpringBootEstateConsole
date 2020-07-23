package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserConverter userConverter;
	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Override
	public List<UserDTO> findByBuildingId(Long buildingId) {
		List<UserEntity> buildingEntities=userRepository.findByBuildings_Id(buildingId);
		List<UserDTO> results=new ArrayList<>();
		for(UserEntity user:buildingEntities) {
			results.add(userConverter.convertEntityToDTO(user));
		}
	return results;
	}
	
	@Override
	public List<UserDTO> getAssignmentBuilding(Long buildingId) {
		List<UserEntity> userEntities = userRepository.findByRole("STAFF");
		List<UserDTO> results = new ArrayList<UserDTO>();
		Optional<BuildingEntity> buildingEntity=buildingRepository.findById(buildingId);
		for(UserEntity user:userEntities) {
				UserDTO userDTO = userConverter.convertEntityToDTO(user);
			 	if(user.getBuildings().contains(buildingEntity.get())) {
			 		userDTO.setCheck("checked");
				}
			 	results.add(userDTO);
			}
		return results;
	}
	@Transactional
	@Override
	public List<UserDTO> saveAssignmentBuilding(Long buildingId,Long [] staffs) {
		userRepository.deleteAssignmentBuilding(buildingId);
		for(Long staff:staffs) {
			userRepository.insertAssignmentBuilding(buildingId, staff);
		}
 		return getAssignmentBuilding(buildingId);
	}

	@Override
	public List<UserDTO> getAssignmentStaff(Long customerId) {
		List<UserEntity> userEntities = userRepository.findByRole("STAFF");
		List<UserDTO> results = new ArrayList<UserDTO>();
		Optional<CustomerEntity> customerEntity=customerRepository.findById(customerId);
		for(UserEntity user:userEntities) {
				UserDTO userDTO = userConverter.convertEntityToDTO(user);
			 	if(user.getCustomers().contains(customerEntity.get())) {
			 		userDTO.setCheck("checked");
				}
			 	results.add(userDTO);
			}
		return results;
	}
	@Transactional
	@Override
	public List<UserDTO> saveAssignmentStaff(Long customerId, Long[] staffs) {
		userRepository.deleteAssignmentCustomer(customerId);
		for(Long staff:staffs) {
			userRepository.insertAssignmentCustomer(customerId, staff);
		}
 		return getAssignmentBuilding(customerId);
	}
	
	@Override
	public List<UserDTO> findByRole(String role) {
		List<UserEntity> userEntities = userRepository.findByRole(role);
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for(UserEntity userEntity:userEntities) {
			userDTOs.add(userConverter.convertEntityToDTO(userEntity));
		}
		return userDTOs;
	}

	@Transactional
	@Override
	public UserDTO save(UserDTO userDTO) {
		UserEntity userEntity = userRepository.save(userConverter.convertDTOToEntity(userDTO));
		return userConverter.convertEntityToDTO(userEntity);
	}

	@Transactional
	@Override
	public void delete(Long [] ids) {
		for(Long id : ids) {
			UserEntity userEntity = userRepository.findById(id).get();
			if(userEntity.getRoles()!=null && userEntity.getRoles().size()>0) {
				userEntity.getRoles().remove(userEntity.getRoles().iterator().next());
			}
			if(userEntity.getBuildings()!=null && userEntity.getRoles().size()>0) {
				userEntity.getBuildings().remove(userEntity.getBuildings().iterator().next());
			}
			userRepository.deleteById(id);
		}
	}

	@Override
	public int totalItem() {
		return (int)userRepository.count();
	}

}
