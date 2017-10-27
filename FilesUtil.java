import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FilesUtil {
	// create new txt file
	public static void createTxtFile(String fileName) throws IOException {
		String path = fileName;
		File file = new File(path);
		// If file doesn't exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}
	}

	// read all txt file
	public static String readTextFile(String fileName) throws IOException {
		String content = new String(Files.readAllBytes(Paths.get(fileName)));
		return content;
	}

	//read txt file line by line and return List 
	public static List<String> readTextFileByLines(String fileName) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(fileName));
		return lines;
	}

	//write to file
	public static void writeToTextFile(String fileName, String content) throws IOException {
		Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
	}

}
