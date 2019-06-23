package com.st.utopia.agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.st.utopia.agent.util.RestTemplateResponseErrorHandler;

@SpringBootApplication
public class UtopiaAgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(UtopiaAgentApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		return restTemplate;
	}
}