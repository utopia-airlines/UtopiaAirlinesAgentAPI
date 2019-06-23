package com.st.utopia.agent.model;

import java.util.Objects;

/**
 * A user of the system, whether an airport clerk, a travel agent or agency, or
 * a (would-be) ticket-holder. A ticket-holder must have an email address or
 * phone number in the database.
 *
 * While I don't expect instances of this class to be modified by user code, and
 * so have not provided setters, fields are left non-final for the JPA.
 *
 * @author Jonathan Lovelace
 *
 */
public class User {
	/**
	 * The user's ID number in the database.
	 */
	private int id;

	/**
	 * The user's username.
	 */
	private String username;

	/**
	 * The name to identify the user in user-facing text. If the user is a
	 * ticket-holder, this is the name to print on the ticket; if the user has
	 * logged in, this is the name to use in the welcome text.
	 */
	private String displayName;

	/**
	 * The user's email address, if any. Ticket-holders must have either an email
	 * address or a phone number in the database.
	 */
	private String email;

	/**
	 * The user's phone number, if any. Ticket-holders must have either an email
	 * address or a phone number in the database.
	 */
	private String phone;

	/**
	 * Default constructor, for JPA.
	 */
	public User() {
	}

	/**
	 * Full-fledged constructor, for tests.
	 */
	public User(final int id, final String username, final String displayName, final String email, final String phone) {
		this.id = id;
		this.username = username;
		this.displayName = displayName;
		this.email = email;
		this.phone = phone;
	}

	/**
	 * @return the user's account-ID number.
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name to use to refer to the user in user-facing text.
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @return the user's email, if known
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the user's phone number, if any
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * We use the user's ID number as the hash code.
	 */
	@Override
	public int hashCode() {
		return id;
	}

	/**
	 * An object is equal iff it is a User with the same id, email, and phone
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof User) {
			return Objects.equals(email, ((User) obj).getEmail())
					&& id == ((User) obj).getId()
					&& Objects.equals(phone, ((User) obj).getPhone());
		} else {
			return false;
		}
	}

	/**
	 * We simply use the user's display name, if not null; if it is null, we use the
	 * username; if that is null, we return "unknown user".
	 */
	@Override
	public String toString() {
		if (displayName == null) {
			if (username == null) {
				return "unknown user";
			} else {
				return username;
			}
		} else {
			return displayName;
		}
	}
}
