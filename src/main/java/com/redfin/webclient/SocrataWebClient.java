package com.redfin.webclient;
import com.redfin.config.ClientConfig;
import com.redfin.model.FoodTruck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

/**
 * Web Client to call the Socrata Food Truck API
 */

@Component
public class SocrataWebClient {
	private static final Logger logger = LoggerFactory.getLogger(SocrataWebClient.class);

	@Autowired
	private static ClientConfig clientConfig;

	public ClientResponse getFoodTrucks() {
		ClientResponse response = null;

		try {

//			Mono<ResponseEntity<FoodTruck>> entityMono = clientConfig.getSocrataWebClient()
//					.get()
//					.accept(MediaType.APPLICATION_JSON)
//					.retrieve()
//					.toEntity(FoodTruck.class);

			response = clientConfig.getSocrataWebClient().get()
					.accept(MediaType.APPLICATION_JSON)
					.exchange().block();
		}
		catch (Exception ex)
		{
			logger.error("Exception: {}", ex.getLocalizedMessage());
		}

		return response;
	}
}
