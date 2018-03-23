package com.rangers.mybatis.sample.pojo;

import java.util.List;

import lombok.Data;

@Data
public class City {
	
	private Long id;
	
	private String name;

	private String state;
	
	private List<Country> countrys;

	public City(String name, String state) {
		super();
		this.name = name;
		this.state = state;
	}

	public City() {
		super();
	}

}
