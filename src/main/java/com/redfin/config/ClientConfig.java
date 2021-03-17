package com.redfin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class ClientConfig {

	private WebClient webClient;

	private String uri = "https://data.sfgov.org/resource/jjew-r69b";

	/**
	 * Returns the LM webclient instance to call external APIs.
	 *
	 * @return webclient instance.
	 */
	public WebClient getSocrataWebClient() {

		if (null == webClient) {
			webClient = WebClient
					.builder()
					.baseUrl(uri)
					.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.build();
		}
		return webClient;
	}
}
