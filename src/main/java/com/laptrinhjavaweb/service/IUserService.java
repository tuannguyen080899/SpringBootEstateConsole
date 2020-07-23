package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.UserDTO;

public interface IUserService {
	List<UserDTO> findByBuildingId(Long buildingId);
	List<UserDTO> findByRole(String role);
	UserDTO save(UserDTO userDTO);
	void delete(Long[]ids);
	List<UserDTO> getAssignmentBuilding(Long buildingId);
	List<UserDTO> saveAssignmentBuilding(Long buildingId,Long [] staffs);
	List<UserDTO> getAssignmentStaff(Long customerId);
	List<UserDTO> saveAssignmentStaff(Long customerId,Long [] staffs);
	int totalItem();
}
