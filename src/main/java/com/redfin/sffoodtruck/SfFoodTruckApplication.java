package com.redfin.sffoodtruck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SfFoodTruckApplication implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(SfFoodTruckApplication.class);

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		logger.info("--------------STARTING SF Food Truck Finder--------------");
		SpringApplication.run(SfFoodTruckApplication.class, args);
		logger.info("--------------SF Food Trucker Finder FINISHED--------------");

	}

	/**
	 * This run method is executed after the Spring application context is loaded (ie. all beans are
	 * effectively created).
	 * @param None
	 * @throws Exception
	 *
	 */
	@Override
	public void run(String... args) throws Exception {
		logger.info("Initializing Run Method");

		FoodTruck foodtruck = restTemplate.getForObject("https://data.sfgov.org/resource/jjew-r69b", FoodTruck.class);
	}

}
