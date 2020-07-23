package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>,BuildingRepositoryCustom {
	//Containing tức là hỗ trợ tìm kiếm theo like	
	List<BuildingEntity> findByNameContainingIgnoreCase(String name);
	List<BuildingEntity> findByDistrictContainingIgnoreCase(String name);
	List<BuildingEntity> findByNameContainingIgnoreCaseAndDistrictContainingIgnoreCase(String name,String district);
}
