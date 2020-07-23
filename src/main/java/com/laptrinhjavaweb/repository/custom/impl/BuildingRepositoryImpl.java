package com.laptrinhjavaweb.repository.custom.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
@Repository
//Phải đặt tên cho đúng đằng sau có đuôi Impl nó mới chạy
public class BuildingRepositoryImpl extends CommonRepository implements BuildingRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	private StringBuilder createSQLSpecial(StringBuilder sql, BuildingSearchBuilder builder) {
		if(StringUtils.isNotBlank(builder.getRentAreaFrom())|| StringUtils.isNotBlank(builder.getRentAreaTo())) {
			sql.append(" and exists(select * from rentarea ra where (A.id=ra.buildingid)");
			if(StringUtils.isNotBlank(builder.getRentAreaFrom())) {
				sql.append(" and ra.value>="+builder.getRentAreaFrom()+"");
			}if(StringUtils.isNotBlank(builder.getRentAreaTo())) {
				sql.append(" and ra.value<="+builder.getRentAreaTo()+"");
			}
			sql.append(")");
		}
		if(builder.getTypes().length>0) {
			sql.append(" and (");
			for(String type:builder.getTypes()) {
				if(type.equals(builder.getTypes()[0])) {
					sql.append(" A.type like '%"+type+"%'");
				}else {
					sql.append(" or A.type like '%"+type+"%'");
				}
			}
			sql.append(")");
			
		//Java 8
//		if(builder.getTypes().length>0) {
//			sql.append(" and (");
//			String sqlType=Arrays.stream(builder.getTypes())
//					.map(item-> "(A.type like '%"+item+"%')")
//					.collect(Collectors.joining(" or"));
//			sql.append(sqlType);
//			sql.append(")");
//		}
		}
		if(StringUtils.isNotBlank(builder.getRentCostFrom())) {
			sql.append(" and A.rentcost >="+builder.getRentAreaFrom()+"");
		}
		if(StringUtils.isNotBlank(builder.getRentCostTo())){
			sql.append(" and A.rentcost <="+builder.getRentCostTo()+"");
		}
		if(builder.getStaffId()!=null&& builder.getStaffId()!=-1) {
			sql.append("and ab.staffid ="+builder.getStaffId()+"");
		}
		return sql;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> search(Map<String, Object> params, BuildingSearchBuilder builder,Pageable pageable) {
		StringBuilder sql=new StringBuilder("select A.name,A.ward,A.street,A.district,A.managername,A.managerphone,A.floorarea,A.rentcost,A.servicecost,A.deposit from building A ");
		if(builder.getStaffId()!=null && builder.getStaffId()!=-1) {
			sql.append(" inner join assignmentbuilding ab on A.id=ab.buildingid ");
		}
		sql=this.createSQLFindAllCommon(sql,params);
		sql=createSQLSpecial(sql,builder);
		//Tương tự như resultsetMapper
		Query query =entityManager.createNativeQuery(sql.toString());
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findAllCustom(Pageable pageable,String property,String order) {
		StringBuilder sql = new StringBuilder("select A.name,A.ward,A.street,A.district,A.managername,");
		sql.append("A.managerphone,A.floorarea,A.rentcost,A.servicecost,A.deposit from building A ");
		sql.append(" order by "+property +" "+order+" ");
		sql.append(" limit "+pageable.getOffset()+", "+pageable.getPageSize()+"");
		Query query =entityManager.createNativeQuery(sql.toString());
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<BuildingEntity> findAllByStaff(Pageable pageable,Long staffid) {
		StringBuilder sql = new StringBuilder("select b from BuildingEntity b join b.staffs s where s.id = :staffid");
		sql.append(" limit "+pageable.getOffset()+", "+pageable.getPageSize()+"");
		Query query =entityManager.createNativeQuery(sql.toString());
		return query.getResultList();
	}
	
}
