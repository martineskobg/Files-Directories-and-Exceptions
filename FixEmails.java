import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FixEmails {

	public static void main(String[] args) throws IOException {

		File output = new File("output.txt");
		if (output.exists()) {
			PrintWriter writer = new PrintWriter(output);
			writer.print("");
			writer.close();
		} else {
			output.createNewFile();
		}

		String inputFromTask = "Ivan\r\n" + "ivanivan@abv.bg\r\n" + "Petar Ivanov\r\n" + "petartudjarov@abv.bg\r\n"
				+ "Mike Tyson\r\n" + "myke@gmail.us\r\n" + "stop\r\n";

		FilesUtil.writeToTextFile("input.txt", inputFromTask);
		List<String> input = FilesUtil.readTextFileByLines("input.txt");
		for (int i = 1; i < input.size(); i += 2) {
			if (input.get(i).equals("stop") || input.get(i - 1).equals("stop")) {
				break;
			}
			if (input.get(i).contains(".us") || input.get(i).contains(".uk")) {
				continue;
			}
			String content = String.format("%s + -> %s%n", input.get(i - 1), input.get(i));
			Files.write(Paths.get("output.txt"), content.getBytes(), StandardOpenOption.APPEND);
		}
		System.out.println("Done");

	}

}
