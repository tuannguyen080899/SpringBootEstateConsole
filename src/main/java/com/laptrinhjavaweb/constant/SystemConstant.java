package com.laptrinhjavaweb.constant;

public class SystemConstant {
	public static final long ACCESS_TOKEN_VADILITY_SECONDS = 3600000;//1h
	//Lớp cuối cùng của lớp bảo mật là SIGNING_KEY
	public static final String SIGNING_KEY = "laptrinhjavaweb";
	//Client gửi về bắt buộc phải có chữ bearer mới cho phép sử dụng token
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER_STRING = "Authorization";
	public static final String AUTHORITIES_KEY = "scopes";
	public static final int ACTIVE = 1;
	public static final int INACTIVE = 0;
}
