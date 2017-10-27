import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MinerTask {

	public static void main(String[] args) throws IOException {

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

		String inputFromTask = "Gold\r\n" + 
				"155\r\n" + 
				"Silver\r\n" + 
				"10\r\n" + 
				"Copper\r\n" + 
				"17\r\n" + 
				"stop\r\n";
				

		FilesUtil.writeToTextFile("input.txt", inputFromTask);
		List<String> input = FilesUtil.readTextFileByLines("input.txt");
		LinkedHashMap<String, Long> resourceAndQuantity = new LinkedHashMap<>();
		
		for (int i = 1; i < input.size(); i += 2) {
			if (input.get(i - 1).equals("stop")) {
				break;
			}
			String key = input.get(i - 1);
			long value = Long.parseLong(input.get(i));
			if (!resourceAndQuantity.containsKey(key)) {
				resourceAndQuantity.put(key, value);
			} else {
				long sum = resourceAndQuantity.get(key) + value;
				resourceAndQuantity.put(key, sum);
			}
		}
		for (String key : resourceAndQuantity.keySet()) {
			String content = String.format("%s + -> %s%n", key, resourceAndQuantity.get(key));
			Files.write(Paths.get("output.txt"), content.getBytes(), StandardOpenOption.APPEND);
		}
		System.out.println("Done");
	}
}
