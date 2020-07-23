package com.laptrinhjavaweb.repository.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.custom.RentAreaRepositoryCustom;
@Repository
public class RentAreaRepositoryImpl implements RentAreaRepositoryCustom{
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<RentAreaEntity> findByBuildingId(long buildingId) {
		String sql = "SELECT r FROM RentAreaEntity r JOIN r.building b WHERE b.id = "+buildingId;
		Query query = entityManager.createQuery(sql);
		return query.getResultList();
	}

}