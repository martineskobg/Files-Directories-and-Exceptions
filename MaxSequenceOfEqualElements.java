import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MaxSequenceOfEqualElements {

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

		String inputFromTask = "7 7 4 4 5 5 3 3";
				

		FilesUtil.writeToTextFile("input.txt", inputFromTask);
		List<String> input = FilesUtil.readTextFileByLines("input.txt");
		

		int count = 0;
		int maxCount = 0;
		int index = 0;
		int[] input2 = parseIntegers(inputFromTask.split("\\s"));
		for (int i = 1; i < input2.length; i++) {
			if (input2[i] == input2[i - 1]) {
				count++;
				if (count > maxCount) {
					maxCount = count;
					index = input2[i];
				} 
			}else {
				count = 0;
			}
		}
		if (index==0) {
			String content = String.format("%d ", input2[0]);
			Files.write(Paths.get("output.txt"), content.getBytes(), StandardOpenOption.APPEND);
		}else{
			for (int i = 0; i < maxCount+1; i++) {
				String content = String.format("%s ", index);
				Files.write(Paths.get("output.txt"), content.getBytes(), StandardOpenOption.APPEND);
			}
		}
		System.out.println("Done");
	}
	public static int[] parseIntegers(String[] input) {
		int[] results = new int[input.length];
		for (int i = 0; i < results.length; i++) {
			results[i] = Integer.parseInt(input[i]);
		}
		return results;
	}
}