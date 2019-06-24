package com.st.utopia.agent.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Composite key for the Ticket class, consisting of the flight (ID, not
 * customer-visible "flight number"), row, and seat.
 *
 * Declared to implement Serializable because "Composite-id class must implement
 * Serializable."
 *
 * @author Jonathan Lovelace
 * @author Salem O
 */
public class SeatLocation {

    /**
     * The flight this seat is on.
     */
    private Flight flight;

    /**
     * The row this seat is in.
     */
    private int row;

    /**
     * Which seat this is in the row.
     */
    private String seat;
    /**
     * Default constructor for JPA.
     */
    public SeatLocation() {
    }
    /**
     * Full constructor for tests.
     */
    public SeatLocation(final Flight flight, final int row, final String seat) {
        this.flight = flight;
        this.row = row;
        this.seat = seat;
    }

    /**
     * @return the flight this seat is on
     */
	public Flight getFlight() {
		return flight;
	}

	/**
	 * @return the row this seat is in
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return which seat this is in the row
	 */
	public String getSeat() {
		return seat;
	}

	@Override
	public int hashCode() {
		return Objects.hash(flight, row, seat);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof SeatLocation) {
			return Objects.equals(flight, ((SeatLocation) obj).getFlight())
					&& row == ((SeatLocation) obj).getRow()
					&& Objects.equals(seat, ((SeatLocation) obj).getSeat());
		} else {
			return false;
		}
	}
}
