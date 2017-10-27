import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MostFrequentNumber {

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
		String inputFromTask = "7 7 7 0 2 2 2 0 10 10 10";
		FilesUtil.writeToTextFile("input.txt", inputFromTask);

		String line = Files.readAllLines(Paths.get("input.txt")).toString().replace("[", "").replace("]", "");
		String[] str = line.split(" ");
		List<Integer> numbers = new ArrayList<>();

		for (String string : str) {
			numbers.add(Integer.parseInt(string));
		}

		ArrayList<Integer> numbers2 = new ArrayList<>(numbers);
		int counter = 0;
		int maxCounter = 0;
		int mostFrequentNumber = 0;
		List<Integer> holdInt = new ArrayList<>();

		for (int i = 0; i < numbers.size(); i++) {
			int num = numbers.get(i);

			for (int j = i + 1; j < numbers2.size(); j++) {
				if (num == numbers2.get(j)) {
					counter++;
					if (counter > maxCounter) {

						holdInt.add(num);
						mostFrequentNumber = num;

						maxCounter = counter;
					}
				}
			}
			counter = 0;
		}

		File file = new File("output.txt");
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(Integer.toString(mostFrequentNumber));
		bw.close(); // Be sure to close BufferedWriter
		System.out.println("Done");
	}
}