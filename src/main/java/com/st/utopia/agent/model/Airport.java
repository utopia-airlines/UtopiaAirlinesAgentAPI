package com.st.utopia.agent.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * An airport that flights can fly to or from.
 *
 * Objects of this class should in general be immutable, so no setters are
 * provided, but to support JPA providers fields are not made final.
 *
 * This class is Serializable because AirportDateDTO has to be and contains a
 * reference to this class.
 *
 * @author Jonathan Lovelace
 * @author Salem O
 */
public class Airport implements Serializable {
	/**
	 * Serialization version. Increment on any change to class structure that is (to
	 * be) pushed to production.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The code identifying the airport.
	 */
	private String code;
	/**
	 * The name of the airport.
	 */
	private String name;

	/**
	 * @return the code identifying the airport
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the name of the airport
	 */
	public String getName() {
		return name;
	}
	/**
	 * Default constructor, provided for JPA.
	 */
	public Airport() {
	}
	/**
	 * Non-default constructor, provided for tests.
	 */
	public Airport(final String code, final String name) {
		this.code = code;
		this.name = name;
	}
	/**
	 * An object is equal to this one iff it is an Airport with the same code.
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof Airport) {
			return Objects.equals(code, ((Airport) obj).getCode());
		} else {
			return false;
		}
	}

	/**
	 * We use the code's hash code as ours.
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(code);
	}

	@Override
	public String toString() {
		return String.format("%s: %s", code, name);
	}
}
