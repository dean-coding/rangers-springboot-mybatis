package com.rangers.mybatis.sample.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rangers.mybatis.sample.dao.CountryMapper;
import com.rangers.mybatis.sample.pojo.Country;

@Service
public class CountryService {

	@Autowired
	private CountryMapper countryMapper;

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public int saveBatch(@Param("countrys")List<Country> countrys) {
		int a = 1 / 0; //事务A异常
		return countryMapper.saveBatch(countrys);
	}
}
