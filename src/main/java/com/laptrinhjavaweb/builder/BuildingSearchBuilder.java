package com.laptrinhjavaweb.builder;

public class BuildingSearchBuilder {
	//Mấy thuộc tính nên để là String thay vì kiểu 
	//nguyên thủy do nếu mình không nhập vào field search
	//thì giá trị mặc định sẽ là 0(là giá trị có nghĩa)
	//và cũng ko nên để kiểu wrapper class
	private String name;
	private String district;
	private String numberOfBasement;
	private String floorArea;
	private String ward;
	private String street;
	private String direction;
	private String level;
	private String rentAreaFrom;
	private String rentAreaTo;
	private String [] types=new String[] {};
	private String rentCostFrom;
	private String rentCostTo;
	private String managerName;
	private String managerPhone;
	//Vì staff id nếu ko chọn thì sẽ để giá trị mặc định là -1
	private Long staffId;
	
	private BuildingSearchBuilder(Builder builder) {
		this.name=builder.name;
		this.district=builder.district;
		this.numberOfBasement=builder.numberOfBasement;
		this.floorArea=builder.floorArea;
		this.ward=builder.ward;
		this.street=builder.street;
		this.direction=builder.direction;
		this.level=builder.level;
		this.rentAreaFrom=builder.rentAreaFrom;
		this.rentAreaTo=builder.rentAreaTo;
		this.types=builder.types;
		this.rentCostFrom=builder.rentCostFrom;
		this.rentAreaTo=builder.rentCostTo;
		this.managerName=builder.managerName;
		this.managerPhone=builder.managerPhone;
		this.staffId=builder.staffId;
	}
	
	public String getName() {
		return name;
	}

	public String getDistrict() {
		return district;
	}

	public String getNumberOfBasement() {
		return numberOfBasement;
	}

	public String getFloorArea() {
		return floorArea;
	}
	
	public String getRentAreaFrom() {
		return rentAreaFrom;
	}
	public String getRentAreaTo() {
		return rentAreaTo;
	}
	
	public String getWard() {
		return ward;
	}

	public String getStreet() {
		return street;
	}

	public String getDirection() {
		return direction;
	}

	public String getLevel() {
		return level;
	}

	public String[] getTypes() {
		return types;
	}
	
	public String getRentCostFrom() {
		return rentCostFrom;
	}

	public String getRentCostTo() {
		return rentCostTo;
	}
	
	public String getManagerName() {
		return managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public Long getStaffId() {
		return staffId;
	}


	public static class Builder{	
		private String name;
		private String district;
		private String numberOfBasement;
		private String ward;
		private String street;
		private String direction;
		private String level;
		private String floorArea;
		private String rentAreaFrom;
		private String rentAreaTo;
		private String [] types=new String[] {};
		private String rentCostFrom;
		private String rentCostTo;
		private String managerName;
		private String managerPhone;
		private Long staffId;
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setDistrict(String district) {
			this.district = district;
			return this;
		}
		public Builder setNumberOfBasement(String numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}
		public Builder setFloorArea(String floorArea) {
			this.floorArea = floorArea;
			return this;
		}
		
		public Builder setRentAreaFrom(String rentAreaFrom) {
			this.rentAreaFrom = rentAreaFrom;
			return this;
		}
		public Builder setRentAreaTo(String rentAreaTo) {
			this.rentAreaTo = rentAreaTo;
			return this;
		}
		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}
		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}
		public Builder setDirection(String direction) {
			this.direction = direction;
			return this;
		}
		public Builder setLevel(String level) {
			this.level = level;
			return this;
		}
		public Builder setTypes(String[] types) {
			this.types = types;
			return this;
		}
		
		public Builder setRentCostFrom(String rentCostFrom) {
			this.rentCostFrom = rentCostFrom;
			return this;
		}
		public Builder setRentCostTo(String rentCostTo) {
			this.rentCostTo = rentCostTo;
			return this;
		}
		public Builder setManagerName(String managerName) {
			this.managerName = managerName;
			return this;
		}
		public Builder setManagerPhone(String managerPhone) {
			this.managerPhone = managerPhone;
			return this;
		}
		public Builder setStaffId(Long staffId) {
			this.staffId = staffId;
			return this;
		}
		
		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}
	}
}
