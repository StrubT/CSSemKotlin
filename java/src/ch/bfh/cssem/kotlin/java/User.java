package ch.bfh.cssem.kotlin.java;

/**
 * Represents a human user of the application.
 *
 * @author strut1 &amp; touwm1
 */
public class User {

	private final String login;
	private String name;

	/**
	 * Constructs a new user with a login but no name.
	 *
	 * @param login login of the user account
	 */
	public User(String login) {
		this(login, login);
	}

	/**
	 * Constructs a new user with a login and a name.
	 *
	 * @param login login of the user account
	 * @param name  name of the person
	 */
	public User(String login, String name) {

		this.login = login;
		this.name = name;
	}

	/**
	 * Gets the login of the user account.
	 *
	 * @return login of the user account
	 */
	public String getLogin() {
		return this.login;
	}

	/**
	 * Gets the name of the person.
	 *
	 * @return name of the person
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of the person.
	 *
	 * @param name name of the person
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets a string representation for the user.
	 *
	 * @return string representation for the user
	 */
	@Override
	public String toString() {
		return String.format("User[login=%s, name=%s]", this.login, this.name);
	}
}
