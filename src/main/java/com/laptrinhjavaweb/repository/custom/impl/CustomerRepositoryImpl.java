package com.laptrinhjavaweb.repository.custom.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.DealEntity;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;
@Repository
public class CustomerRepositoryImpl extends CommonRepository implements CustomerRepositoryCustom{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerEntity> findAll(Map<String, Object> params, CustomerSearchBuilder builder, Pageable pageable) {
		StringBuilder sql=new StringBuilder("select * from customer A ");
		if(builder.getStaffId()!=null && builder.getStaffId()!=-1) {
			sql.append(" inner join assignmentcustomer ac on A.id=ac.customer_id ");
		}
		sql.append(" where 1=1");
		sql.append(" limit "+pageable.getOffset()+", "+pageable.getPageSize()+"");
		sql=this.createSQLFindAllCommon(sql, params);
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setFirstResult((int)pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DealEntity> findDealsByCustomer(Long customerId,Pageable pageable) {
		StringBuilder sql=new StringBuilder("select * from deal A where A.customerid = :customerId");
		Query query = entityManager.createNativeQuery(sql.toString());
		query.setFirstResult((int)pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		return query.getResultList();
	}

}
