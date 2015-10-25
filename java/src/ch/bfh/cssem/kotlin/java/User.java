package ch.bfh.cssem.kotlin.java;

public class User {

	private final String login;
	private String name;

	public User(String login) {
		this(login, login);
	}

	public User(String login, String name) {

		this.login = login;
		this.name = name;
	}

	public String getLogin() {
		return this.login;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("User[login=%s, name=%s]", this.login, this.name);
	}
}
