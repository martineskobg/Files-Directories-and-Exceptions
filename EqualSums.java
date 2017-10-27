import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class EqualSums {

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

		String inputFromTask = "10 5 5 99 3 4 2 5 1 1 4";
		
		String[] input2 =inputFromTask.split("\\s");
		
		FilesUtil.writeToTextFile("input.txt", inputFromTask);


		int[] intArray = Arrays.stream(input2).mapToInt(i -> Integer.parseInt(i)).toArray();

		
		boolean isFindEqualSum = false;

		for (int i = 0; i < intArray.length; i++) {
			int[] leftSum = Arrays.stream(intArray).limit(i).toArray();
			int[] rightSum = Arrays.stream(intArray).skip(i + 1).toArray();

			int sumLeft = IntStream.of(leftSum).sum();
			int sumRight = IntStream.of(rightSum).sum();

			if (sumLeft == sumRight) {
				String content = String.format("%d", i);
				Files.write(Paths.get("output.txt"), content.getBytes(), StandardOpenOption.APPEND);
				isFindEqualSum = true;
				break;
			}
		}

		if (!isFindEqualSum) {
			String content = "no";
			Files.write(Paths.get("output.txt"), content.getBytes(), StandardOpenOption.APPEND);
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