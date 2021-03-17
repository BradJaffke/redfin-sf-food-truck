package com.redfin.sffoodtruck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import com.redfin.model.FoodTruck;

import java.time.Duration;
import java.util.Arrays;

@SpringBootApplication
public class SfFoodTruckApplication implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(SfFoodTruckApplication.class);

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

		final String uri = "https://data.sfgov.org/resource/jjew-r69b.json";
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
	}

}
