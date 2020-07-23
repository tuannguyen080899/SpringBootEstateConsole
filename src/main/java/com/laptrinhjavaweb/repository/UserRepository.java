package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.custom.UserRepositoryCustom;

public interface UserRepository extends JpaRepository<UserEntity,Long>,UserRepositoryCustom{
	List<UserEntity> findByBuildings_Id(long id);
	UserEntity findOneByUserNameAndStatus(String username, int active);
}
