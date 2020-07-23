package com.laptrinhjavaweb.repository.custom;

import java.util.List;

import com.laptrinhjavaweb.entity.UserEntity;

public interface UserRepositoryCustom {
List<UserEntity> findByBuildingId(long buildingId);
List<UserEntity> findByRole(String role);
void insertAssignmentBuilding(long buildingId, long staff);
void deleteAssignmentBuilding(long buildingId);
void insertAssignmentCustomer(long customerId, long staff);
void deleteAssignmentCustomer(long customerId);
}
