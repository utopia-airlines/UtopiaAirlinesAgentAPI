package com.st.utopia.agent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import com.st.utopia.agent.model.Airport;

/**
 * Controller for agents using this user-facing interface
 *
 * @author Jonathan Lovelace
 * @author Salem O
 */
@RestController
@CrossOrigin
public class Airports {
	@Autowired
	private RestTemplate restTemplate;

	@Value("${utopia.search.API}")
	private String searchAPI;
	
	/**
	 * Helper method to reduce the amount of repetitive code required for a method call.
	 * methods.
	 * @param url the URL to send the REST request to
	 * @param <T> the type we expect
	 * @return the response the server sent
	 */
	private <T> ResponseEntity<T> methodCall(final String url, final HttpMethod method) {
		return restTemplate.exchange(url, method, null,
				new ParameterizedTypeReference<T>() {});
	}

	/**
	 * Get list of airports
	 * 
	 * @return list of airports
	 */
	@GetMapping("/airports")
	public ResponseEntity<Airport> getAllAirports() {
		String url = searchAPI + "/airports";
		return this.<Airport>methodCall(url, HttpMethod.GET);
	}

	/**
	 * Get a list of airports matching the given code
	 * @param airport code
	 * 
	 * @return list of airports
	 */
	@GetMapping("/airports/{code}")
	public ResponseEntity<Airport> getAllAirportsWithId(
			@PathVariable final String code) {
		String url = searchAPI + "/airportDetails?airport=" + code;
		return this.<Airport>methodCall(url, HttpMethod.GET);
	}
}
