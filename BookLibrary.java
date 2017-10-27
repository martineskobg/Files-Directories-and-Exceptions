import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BookLibrary {

	public static void main(String[] args) throws IOException, ParseException {
		File output = new File("output.txt");
		File fInput = new File("input.txt");

		if (output.exists()) {
			PrintWriter writer = new PrintWriter(output);
			writer.print("");
			writer.close();
		} else {
			output.createNewFile();
		}

		if (fInput.exists()) {
			PrintWriter writer = new PrintWriter(fInput);
			writer.print("");
			writer.close();
		}

		String inputFromTask = "5\r\n" + "LOTR Tolkien GeorgeAllen 29.07.1954 0395082999 30.00\r\n"
				+ "Hobbit Tolkien GeorgeAll 21.09.1937 0395082888 10.25\r\n"
				+ "HP1 JKRowling Bloomsbury 26.06.1997 0395082777 15.50\r\n"
				+ "HP7 JKRowling Bloomsbury 21.07.2007 0395082666 20.00\r\n"
				+ "AC OBowden PenguinBooks 20.11.2009 0395082555 14.00\r\n";

		FilesUtil.writeToTextFile("input.txt", inputFromTask);
		List<String> inputList = FilesUtil.readTextFileByLines("input.txt");
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		int count = Integer.parseInt(inputList.get(0));

		Library library = new Library();
		Library.name = "Bachev";

		for (int i = 0; i < count; i++) {
			Book book = new Book();
			String[] input = inputList.get(i + 1).split(" ");

			book.title = input[0];
			book.author = input[1];
			book.publisger = input[2];
			book.releaseDate = df.parse(input[3]);
			book.ISBN = input[4];
			book.price = Double.parseDouble(input[5]);
			library.books.add(book);
		}

		HashMap<String, Double> filteredBooks = new HashMap<>();
		for (Book book : library.books) {

			String tempAuthor = book.author;

			if (!filteredBooks.containsKey(tempAuthor)) {
				filteredBooks.put(tempAuthor, book.price);
			} else {
				filteredBooks.put(tempAuthor, book.price + filteredBooks.get(tempAuthor));
			}

		}

		filteredBooks.forEach((k, v) -> {
			String result = k + " -> " + v + "\r\n";
			try {
				Files.write(Paths.get("output.txt"), result.getBytes(), StandardOpenOption.APPEND);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		System.out.println("Done");

	}
}
