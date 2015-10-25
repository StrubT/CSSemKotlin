package ch.bfh.cssem.kotlin.java;

public class User {

	private String login;

	public User(String login) {

		this.login = login;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return String.format("User[login=%s]", this.login);
	}
}
