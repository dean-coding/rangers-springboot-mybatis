package com.rangers.mybatis.sample.pojo;

import lombok.Data;

@Data
public class Country {
	
	private Long id;
	
	private Long cityId;
	
	private String name;

	public Country(Long cityId, String name) {
		super();
		this.cityId = cityId;
		this.name = name;
	}

	public Country() {
		super();
	}

	
	
}
