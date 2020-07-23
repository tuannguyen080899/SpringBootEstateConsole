package com.laptrinhjavaweb.repository.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.custom.UserRepositoryCustom;

@Repository
public class UserRepositoryImpl extends CommonRepository implements UserRepositoryCustom{
	@PersistenceContext
	private EntityManager entityManager;

	//SQL Native
	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> findByBuildingId(long buildingId) {
		StringBuilder sql=new StringBuilder("select * from user u inner join assignmentbuilding ab on u.id = ab.staffid");
		sql.append(" where 1=1");
		sql.append(" AND ab.buildingid = :buildingId");
		//Hỗ trợ việc đổ data từ resultSet sang đối tượng cần map 
		Query query =entityManager.createNativeQuery(sql.toString(), UserEntity.class);
		query.setParameter("buildingId", buildingId);
		return query.getResultList();
	}
	//HQL
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<UserEntity> findByBuildingId(long buildingId) {
//		//Cú pháp manytomany
//		StringBuilder sql=new StringBuilder("select u from UserEntity u join u.buildings b ");
//		sql.append(" where 1=1");
//		sql.append(" AND b.id = :buildingId");
//		//Tương tự như resultsetMapper
//		Query query =entityManager.createQuery(sql.toString());
//		query.setParameter("buildingId", buildingId);
//		return query.getResultList();
//	}	
	
	@Override
	public void insertAssignmentBuilding(long buildingId, long staff) {
		StringBuilder sql=new StringBuilder("insert into assignmentbuilding  (building_id,staff_id) values(?,?)");
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, buildingId);
		query.setParameter(2, staff);
		query.executeUpdate();
	}

	@Override
	public void deleteAssignmentBuilding(long buildingId) {
			StringBuilder sql=new StringBuilder("delete from assignmentbuilding where building_id = :buildingId");
			Query query = entityManager.createNativeQuery(sql.toString());
			query.setParameter("buildingId", buildingId);
			query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> findByRole(String role) {
		StringBuilder sql= new StringBuilder("select u from UserEntity u join u.roles r where r.code = :role") ;
		//String sql="select u from user_role join  where r.code = :role ";
		Query query = entityManager.createQuery(sql.toString());
		query.setParameter("role", role);
		return query.getResultList();
	}

	@Override
	public void insertAssignmentCustomer(long customerId, long staff) {
		StringBuilder sql=new StringBuilder("insert into assignmentcustomer  (customer_id,staff_id) values(?,?)");
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, customerId);
		query.setParameter(2, staff);
		query.executeUpdate();
	}

	@Override
	public void deleteAssignmentCustomer(long customerId) {
		StringBuilder sql=new StringBuilder("delete from assignmentcustomer where customer_id = :customerId");
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter("customerId", customerId);
		query.executeUpdate();
	}
}
