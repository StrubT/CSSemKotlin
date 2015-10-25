package ch.bfh.cssem.kotlin.java;

import java.util.Arrays;
import java.util.List;

/**
 * Represents the entry point to the application.
 *
 * @author strut1 &amp; touwm1
 */
public final class Program {

	private Program() {}

	/**
	 * Runs the test statements.
	 *
	 * @param args arguments from command line (none used)
	 */
	public static void main(String... args) {

		List<User> users = Arrays.asList(
			new User("strut1", "Strub, Thomas Reto"),
			new User("touwm1"),
			new User("weidj1"));

		users.get(1).setName("Touw, Marc");
		users.forEach(System.out::println);
	}
}
