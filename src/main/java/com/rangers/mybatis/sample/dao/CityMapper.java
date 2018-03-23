package com.rangers.mybatis.sample.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.rangers.mybatis.sample.pojo.City;

@Mapper
public interface CityMapper {
	
	City findByNameDefine(String name);

	City findById(Integer id);
	
	int save(City city);

	List<City> selectAll();
	
	List<City> selectAllDetail();
	
	List<City> findAll(@Param("params") City params);
}
