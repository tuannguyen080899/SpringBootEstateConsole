package com.laptrinhjavaweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "deal" )
public class DealEntity extends BaseEntity{
	
	@ManyToOne
    @JoinColumn(name = "customerid")
	private CustomerEntity customer;
	
	@Column(name = "code")
	private String code;
	
	@Column(name="note")
	private String note;
	
}
