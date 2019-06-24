package com.st.utopia.agent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.st.utopia.agent.model.Airport;
import com.st.utopia.agent.model.Ticket;

/**
 * Controller for agents using this user-facing interface
 *
 * @author Jonathan Lovelace
 * @author Salem O
 */
@RestController
public class Airports {
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
	 * Get list of airports
	 * 
	 * @return list of airports
	 */
	@GetMapping("/airports")
	public ResponseEntity<Airport> getAllAirports() {
		// FIXME: Search service doesn't yet provide this
		String url = searchAPI + "/airports";
		return this.<Airport>methodCall(url, HttpMethod.GET);
	}

	/**
	 * Reserve a ticket for the given seat.
	 * @param flight the flight number of the flight
	 * @param row the row number of the seat
	 * @param seat the seat within the row
	 * @param user the user details
	 * 
	 * @return the ticket that was created by this post
	 */
	@GetMapping("/airports/{code}")
	public ResponseEntity<Ticket> getAllAirportsWithId(
			@PathVariable final String code) {
		// FIXME: Search service doesn't yet provide this
		String url = searchAPI + "/airportDetails?airport=" + code;
		return this.<Ticket>methodCall(url, HttpMethod.GET);
	}
}
