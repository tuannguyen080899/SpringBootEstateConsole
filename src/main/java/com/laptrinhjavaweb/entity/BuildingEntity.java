package com.laptrinhjavaweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table( name = "building" )
public class BuildingEntity extends BaseEntity{
		
	@Column(name="name")
	private String name;
	
	@Column(name="ward")
	private String ward;
	
	@Column(name="street")
	private String street;
	
	@Column(name="structure")
	private String structure;
	
	@Column(name="numberofbasement")
	private Integer numberOfBasement;
	
	@Column(name="floorarea")
	private Integer floorArea;
	
	@Column(name="direction")
	private String direction;
	
	@Column(name="level")
	private String level;	
	
	@Column(name="rentareadescription")
	private String rentAreaDescription;
	
	@Column(name="rentcost")
	private String rentCost;
	
	@Column(name="costdescription")
	private String costDescription;
	
	@Column(name="servicecost")
	private String serviceCost;
	
	@Column(name="carcost")
	private String carCost;
	
	@Column(name="motorcost")
	private String motorCost;
	
	@Column(name="overtimecost")
	private String overtimeCost;
	
	@Column(name="district")
	private String district;
	
	@Column(name="electricbill")
	private String electricBill;
	
	@Column(name="deposit")
	private String deposit;
	
	@Column(name="payment")
	private String payment;
	
	@Column(name="timerent")
	private String timeRent;
	
	@Column(name="timedecorator")
	private String timeDecorator;
	
	@Column(name="managername")
	private String managerName;
	
	@Column(name="managerphone")
	private String managerPhone;
	
	//orphanRemoval cho phép xóa dữ liệu trên bảng (many)
	@OneToMany(mappedBy = "building", orphanRemoval =true, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private List<RentAreaEntity> rentAreas = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "assignmentbuilding", 
				joinColumns = @JoinColumn(name = "building_id"), 
				inverseJoinColumns = @JoinColumn(name = "staff_id"))
	private List<UserEntity> staffs = new ArrayList<>();
	
	@Column(name="type")
	private String types;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}

	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}

	public Integer getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getRentAreaDescription() {
		return rentAreaDescription;
	}

	public void setRentAreaDescription(String rentAreaDescription) {
		this.rentAreaDescription = rentAreaDescription;
	}

	public String getRentCost() {
		return rentCost;
	}

	public void setRentCost(String rentCost) {
		this.rentCost = rentCost;
	}

	public String getCostDescription() {
		return costDescription;
	}

	public void setCostDescription(String costDescription) {
		this.costDescription = costDescription;
	}

	public String getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(String serviceCost) {
		this.serviceCost = serviceCost;
	}

	public String getCarCost() {
		return carCost;
	}

	public void setCarCost(String carCost) {
		this.carCost = carCost;
	}

	public String getMotorCost() {
		return motorCost;
	}

	public void setMotorCost(String motorCost) {
		this.motorCost = motorCost;
	}

	public String getOvertimeCost() {
		return overtimeCost;
	}

	public void setOvertimeCost(String overtimeCost) {
		this.overtimeCost = overtimeCost;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getElectricBill() {
		return electricBill;
	}

	public void setElectricBill(String electricBill) {
		this.electricBill = electricBill;
	}

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getTimeRent() {
		return timeRent;
	}

	public void setTimeRent(String timeRent) {
		this.timeRent = timeRent;
	}

	public String getTimeDecorator() {
		return timeDecorator;
	}

	public void setTimeDecorator(String timeDecorator) {
		this.timeDecorator = timeDecorator;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public List<RentAreaEntity> getRentAreas() {
		return rentAreas;
	}

	public void setRentAreas(List<RentAreaEntity> rentAreas) {
		this.rentAreas = rentAreas;
	}
	
	public List<UserEntity> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<UserEntity> staffs) {
		this.staffs = staffs;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

}
