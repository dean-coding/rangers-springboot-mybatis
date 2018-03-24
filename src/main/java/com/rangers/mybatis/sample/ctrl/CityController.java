/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.rangers.mybatis.sample.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rangers.mybatis.sample.dao.CityMapper;
import com.rangers.mybatis.sample.pojo.City;
import com.rangers.mybatis.sample.pojo.Country;
import com.rangers.mybatis.sample.service.CountryService;

@RestController
@RequestMapping("cities")
public class CityController {

	@Autowired
	private CityMapper mapper;

	@Autowired
	private CountryService countryService;

	@PostMapping(value = "/add")
	public City add(@RequestBody City city) {
		mapper.save(city);
		return city;
	}

	

	/**
	 * 事务的默认传播特性：propagation=Propagation.REQUIRED 当前事务；
	 * Propagation.REQUIRES_NEW 启动新的事务；完全隔离；
	 * 当此事务开始执行时, 外部事务将被挂起, 事务结束时, 外部事务将继续执行.虽然嵌套但是事务之间状态相互无影响
	 * 
	 * Propagation.NESTED； 启动一个 "嵌套的" 事务,依赖于父事务；嵌套事务开始执行时, 它将取得一个 savepoint. 
	 * 如果这个嵌套事务失败, 我们将回滚到此savepoint. 潜套事务是外部事务的一部分, 只有外部事务结束后它才会被提交.
	 * 
	 * 示例结果：（事务A,B在两个不同的类中，因为@Transactional，基于AOP,针对目标类做动态代理的）
	 * Propagation.NESTED : B->成功 ;  A->异常 ，回滚;  B->回滚；
	 * Propagation.REQUIRES_NEW : B->成功，提交; A->异常，回滚;
	 * 	B->失败，回滚；A->（可以try...catch 捕获B的异常，也可以成功提交）异常，回滚；
	 */
	@PostMapping(value = "/add/country")
	@Transactional
	public City addAndCountry(@RequestBody City city) {
		List<Country> countrys = city.getCountrys();
		try {
			if (countrys != null && countrys.size() > 0) {
				countryService.saveBatch(countrys);// 嵌套事务B,
			}
//		int a = 1 / 0; //事务A异常
		} catch (Exception e) {
			e.printStackTrace();
		}
		mapper.save(city);
		return city;
	}

	@GetMapping(value = "/byid/{id}")
	public City view(@PathVariable Integer id) {
		return mapper.findById(id);
	}

	@GetMapping("/list")
	public List<City> list() {
		return mapper.selectAll();
	}

	@GetMapping("/list/details")
	public List<City> listDetail() {
		return mapper.selectAllDetail();
	}

	@GetMapping("/query")
	public List<City> query(City params) {
		return mapper.findAll(params);
	}

	@GetMapping(value = "/byname/{name}")
	public City findByNameDefine(@PathVariable String name) {
		return mapper.findByNameDefine(name);
	}
}
