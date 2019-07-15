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

import com.st.utopia.agent.model.Flight;

@RestController
@CrossOrigin
public class Flights {
	@Autowired
	private RestTemplate restTemplate;

	@Value("${utopia.search.API}")
	private String searchAPI;
	/**
	 * Helper method to reduce the amount of repetitive code required for "get-all"
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
	 * Get list of flights
	 * 
	 * @return list of flights
	 */
	@GetMapping("/flights")
	public ResponseEntity<Flight> getAllFlights() {
		String url = searchAPI + "/flights";
		return this.<Flight>methodCall(url, HttpMethod.GET);
	}

	/**
	 * Get a list of airports matching the given code
	 * @param airport code
	 * 
	 * @return list of airports
	 */
	@GetMapping("/flights/{flightId}")
	public ResponseEntity<Flight> getAllFlightsWithId(
			@PathVariable final String flightId) {
		String url = searchAPI + "/flightDetails?flight=" + flightId;
		return this.<Flight>methodCall(url, HttpMethod.GET);
	}
}
