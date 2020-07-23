package com.laptrinhjavaweb.repository.custom;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;


//Phải đặt tên đúng cho những interface cần custom lại 
//bổ sung thêm các hàm mình tự viết (phía sau có Entity+Repository+ Custom)
public interface BuildingRepositoryCustom {
	List<Object> findAllCustom(Pageable pageable,String property, String order);
	List<BuildingEntity> findAllByStaff(Pageable pageable,Long staffid);
	List<Object> search(Map<String,Object> params,BuildingSearchBuilder builder,Pageable pageable);
}
