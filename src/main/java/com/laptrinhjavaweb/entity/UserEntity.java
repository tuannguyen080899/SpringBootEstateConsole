package com.laptrinhjavaweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table( name = "user" )
public class UserEntity extends BaseEntity{

	@Column(name="username")
	private String userName;
	
	@Column(name="fullname")
	private String fullName;
	
	@Column(name="password")
	private String passWord;
	
	@Column(name="status")
	private String status;
	
	@ManyToMany
	@JoinTable(name = "user_role", 
			joinColumns = @JoinColumn(name = "user_id"), 
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<RoleEntity> roles = new ArrayList<>();
	
	@ManyToMany(mappedBy = "staffs")
    private List<BuildingEntity> buildings = new ArrayList<>();
	
	@ManyToMany(mappedBy = "staffs")
    private List<CustomerEntity> customers = new ArrayList<>();
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public List<BuildingEntity> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<BuildingEntity> buildings) {
		this.buildings = buildings;
	}

	public List<CustomerEntity> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerEntity> customers) {
		this.customers = customers;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}
	
}
