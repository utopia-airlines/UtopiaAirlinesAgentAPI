package com.st.utopia.agent.model;

/**
 * A simple wrapper around a single integer representing an amount paid for a
 * ticket.
 *
 * @author Jonathan Lovelace
 */
public class PaymentAmount {
	/**
	 * The amount paid.
	 */
	private Integer price;
	/**
	 * @return the amount paid
	 */
	public Integer getPrice() {
		return price;
	}
}
