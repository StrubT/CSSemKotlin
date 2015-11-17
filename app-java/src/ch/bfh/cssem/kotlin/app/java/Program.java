package ch.bfh.cssem.kotlin.app.java;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;
import ch.bfh.cssem.kotlin.api.AddressBook;

public final class Program {

	private Program() {
		//utility class
	}

	public static void main(String... args) {

		if (args.length > 0)
			throw new IllegalArgumentException("No command-line arguments supported!");

		AddressBook book = Program.getServiceImplementation(AddressBook.class);
		book.fetchAllPeople().forEach(System.out::println);
	}

	private static <T> T getServiceImplementation(Class<T> type) {

		Iterator<T> loader = ServiceLoader.load(type).iterator();

		if (!loader.hasNext())
			throw new NoSuchElementException("No API implementations found.");

		T service = loader.next();

		if (loader.hasNext())
			throw new IllegalArgumentException("Multiple API implementations found.");

		return service;
	}
}
