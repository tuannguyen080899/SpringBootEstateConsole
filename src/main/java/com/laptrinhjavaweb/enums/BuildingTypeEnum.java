package com.laptrinhjavaweb.enums;

public enum BuildingTypeEnum {
	NOI_THAT ("Nội thất"),
	TANG_TRET ("Tầng trệt"),
	NGUYEN_CAN ("Nguyên căn");
	
	private  String value;
	
	private BuildingTypeEnum(String value) {
		this.value=value;
	}

	public String getValue() {
		return value;
	}
}
