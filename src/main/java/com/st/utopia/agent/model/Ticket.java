package com.st.utopia.agent.model;

import java.time.LocalDateTime;

/**
 * A ticket, or seat (for which a ticket may be or may have been sold), on a
 * flight.
 *
 * Unlike most model classes, several fields in this class are expected to be
 * modified as tickets are booked (and cancelled, but that's out of the scope of
 * this service), and so have setters. Other fields are not expected to be
 * modified, and so do not have setters, but are left non-final for the JPA
 * persistence system. It's possible to get an instance of this class into an
 * invalid state using the provided setters; the isValid() method is provided to
 * check validity, and callers are expected to pass only valid objects to other
 * code. But some setters do some basic sanity checks.
 *
 * @author Jonathan Lovelace
 * @author Salem O
 *
 */
public class Ticket {
	/**
	 * What flight this seat/ticket is for, and where in the plane it is.
	 */
	private SeatLocation id;
	/**
	 * What class of seat this is. For now first class is "1", business class is
	 * "2", and economy is "3". TODO: make an enum?
	 */
	private int seatClass;
	/**
	 * The price that the ticket-holder paid to book this seat; must be null if
	 * reserver is null or if both reserver and reservationTimeout are not null, but
	 * must not be null if reserver is not null but reservationTimeout is.
	 */
	private Integer price;
	/**
	 * If not null, how long the user who reserved this seat has to confirm that
	 * reservation by paying for the ticket. Must be null if reserver is null or if
	 * both reserver and price are not null, but must not be null if reserver is not
	 * null but price is.
	 */
	private LocalDateTime reservationTimeout;

	/**
	 * The "ID" that customers can use to refer to their booking; it is (intended to
	 * be) a hash of the flight, row, seat, and reserver, and must be set when
	 * 'reserver' is set.
	 */
	private String bookingId;
	/**
	 * Default constructor for JPA.
	 */
	public Ticket() {
	}
	/**
	 * More complete constructor for tests.
	 */
	public Ticket(final SeatLocation id, final int seatClass) {
		this.id = id;
		this.seatClass = seatClass;
	}

	/**
	 * @return the flight, row, and seat that together uniquely identify this
	 *         ticket.
	 */
	public SeatLocation getId() {
		return id;
	}

	/**
	 * @return the class of the seat
	 */
	public int getSeatClass() {
		return seatClass;
	}

	/**
	 * @return the price the ticket-holder paid, if the ticket is reserved and he or
	 *         she has paid
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * @return when the reservation will time out if the user who reserved the
	 *         ticket has not paid by then
	 */
	public LocalDateTime getReservationTimeout() {
		return reservationTimeout;
	}

	/**
	 * @return the "booking ID" shared with the ticket-holder, or null if not yet booked.
	 */
	public String getBookingId() {
		return bookingId;
	}
}
