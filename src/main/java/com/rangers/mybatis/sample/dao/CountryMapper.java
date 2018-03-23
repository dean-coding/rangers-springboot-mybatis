package com.rangers.mybatis.sample.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.rangers.mybatis.sample.pojo.Country;

@Mapper
public interface CountryMapper {
	
	int save(Country country);
	
	int saveBatch(@Param("countrys")List<Country> countrys);
}
