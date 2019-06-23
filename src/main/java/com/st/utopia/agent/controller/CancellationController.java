package com.st.utopia.agent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.st.utopia.agent.model.Ticket;

@RestController
@RequestMapping("/cancel")
public class CancellationController {

	@Autowired
	RestTemplate restTemplate;

	@Value("${utopia.cancel-service-hostName}")
	private String hostName;

	@Value("${utopia.cancel-service-port}")
	private String port;
	
	@Value("${utopia.cancel-service-controller-root}")
	private String root;
	
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
	 * Only the holder of the Ticket should be able to cancel the reservation, or
	 * the agent if the ticket is booked from the counter.using the composite ID
	 * 
	 */
	@PutMapping("/ticket/flight/{flightNumber}/row/{rowNumber}/seat/{seat}")
	public ResponseEntity<Ticket> cancelReservationById(@PathVariable int flightNumber, @PathVariable int rowNumber,
			@PathVariable char seat) {
		String url = "http://" + hostName + ":" + port + root + "/ticket/flight/" + flightNumber +
				"/row/" + rowNumber + "/seat/" + seat;
		return this.<Ticket>methodCall(url, HttpMethod.PUT);
	}
	
	/**
	 * Only the holder of the Ticket should be able to cancel the reservation, or
	 * the agent if the ticket is booked from the counter. using the Booking ID 
	 * 
	 */
	@PutMapping("/ticket/booking-id/{bookingId}")
	public ResponseEntity<Ticket> cancelReservationByBookingId(@PathVariable String bookingId) {
		String url = "http://" + hostName + ":" + port + root + "/ticket/booking-id/" + bookingId;
		return this.<Ticket>methodCall(url, HttpMethod.PUT);
	}
}
