import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IndexOfLetters {

	public static void main(String[] args) throws IOException {
		
		char[] alphabet = new char[26];
		int index = 0;
		for (int i = 97; i <= 122; i++) {
			alphabet[index] = (char) i;
			index++;
		}
		
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

		String inputFromTask = "abcz";
		
		FilesUtil.writeToTextFile("input.txt", inputFromTask);
		
		String line = Files.readAllLines(Paths.get("input.txt")).toString().replace("[", "").replace("]", "");
		
		File file = new File("output.txt");
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		for (int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			for (int j = 0; j < alphabet.length; j++) {
				if (ch == alphabet[j]) {
					bw.write(String.format("%s -> %d%n", ch,j));
				}
			}
		}
		bw.close();
		System.out.println("Done");
	}
}
