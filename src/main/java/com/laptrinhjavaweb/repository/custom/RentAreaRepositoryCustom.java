package com.laptrinhjavaweb.repository.custom;

import java.util.List;

import com.laptrinhjavaweb.entity.RentAreaEntity;

public interface RentAreaRepositoryCustom {
	List<RentAreaEntity> findByBuildingId(long buildingId);
}
