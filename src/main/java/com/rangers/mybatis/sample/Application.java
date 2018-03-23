package com.rangers.mybatis.sample;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rangers.mybatis.sample.dao.CityMapper;
import com.rangers.mybatis.sample.dao.CountryMapper;
import com.rangers.mybatis.sample.pojo.City;
import com.rangers.mybatis.sample.pojo.Country;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner initData(CityMapper cityMapper, CountryMapper countryMapper) {
		return (args) -> {
			City city = new City("安徽", "风景好");
			cityMapper.save(city);
			Long cityId = city.getId();
			List<Country> countrys = Arrays.asList(new Country(cityId, "涡阳"), new Country(cityId, "蒙城"),
					new Country(cityId, "利津"));
			countryMapper.saveBatch(countrys);
		};
	}

	@Configuration
	@EnableSwagger2
	public static class SwaggerConfig {

		@Bean
		public Docket api() {
			// @formatter:off
			return new Docket(DocumentationType.SWAGGER_2).select()
					.apis(RequestHandlerSelectors.basePackage("com.rangers.mybatis.sample.ctrl"))
					.paths(PathSelectors.any()).build().apiInfo(apiInfo());
			// @formatter:on
		}

		private ApiInfo apiInfo() {
			// @formatter:off
			return new ApiInfoBuilder().title("测试API").description("测试API-API 文档").version("v1.0").build();
			// @formatter:on
		}

	}

}
