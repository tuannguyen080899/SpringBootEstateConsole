package com.laptrinhjavaweb.repository.custom.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class CommonRepository {
	protected StringBuilder createSQLFindAllCommon(StringBuilder sql, Map<String, Object> params) {
		if(params!=null && params.size()>0) {
			//Tạo ra 2 mảng 1 mảng key 1 mảng value
			String[] keys=new String[params.size()];
			Object[]values=new Object[params.size()];
			int i=0;
			for(Map.Entry<String, Object> item:params.entrySet()) {
				keys[i]=item.getKey();
				values[i]=item.getValue();
				i++;
			}
			for(int i1=0;i1<keys.length;i1++) {
				if(values[i1] instanceof String) {
					//Kiểm tra String khác null vs khác empty
					if(StringUtils.isNotBlank(values[i1].toString())) {
					// mục đích là lấy giá trị tương ứng của key và value truyền vào sql
						sql.append(" and A."+keys[i1] +" like '%"+values[i1].toString()+"%'");
					}
				}else {
					if(values[i1] != null) {
					sql.append(" and A."+keys[i1] +" = "+values[i1]+"");
					}
				}
			}
		}
		return sql;
	}
}
