package com.laptrinhjavaweb.enums;

public enum DealTypeEnum {
	
	CUSTOMER_SERVICE("CHĂM SÓC KHÁCH HÀNG"),
	LEAD_CUSTOMER_TO_VISIT("DẪN ĐI XEM");
	
	private  String value;
	
	private DealTypeEnum(String value) {
		this.value=value;
	}

	public String getValue() {
		return value;
	}
}
