package ch.bfh.cssem.kotlin.java;

public final class Program {

	private Program() {}

	public static void main(String... args) {

		User[] users = {
			new User("strut1"),
			new User("touwm1")
		};

		for (User user : users)
			System.out.println(user);
	}
}
