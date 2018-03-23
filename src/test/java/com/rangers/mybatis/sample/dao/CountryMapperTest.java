package com.rangers.mybatis.sample.dao;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rangers.mybatis.sample.pojo.Country;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryMapperTest {

	@Autowired
	private CountryMapper mapper;

	@Test
	public void saveTest() {
		Country country = new Country(1L, "涡阳");
		mapper.save(country);
		Assert.assertNotNull(country);
		Assert.assertNotNull(country.getId());
	}

	@Test
	public void saveBatchTest() {
		List<Country> countrys = Arrays.asList(new Country(1L, "涡阳"), new Country(1L, "蒙城"), new Country(1L, "立信"));
		int saveBatch = mapper.saveBatch(countrys);
		Assert.assertNotNull(saveBatch);
		Assert.assertEquals(saveBatch, countrys.size());
	}

}
