import java.util.*;
import java.util.concurrent.*;

import javax.management.DescriptorRead;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Struct;

public class AverageGrades {

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

		String inputFromTask = "6\r\n" + "Petar 3 5 4 3 2 5 6 2 6\r\n" + "Mitko 6 6 5 6 5 6\r\n"
				+ "Gosho 6 6 6 6 6 6\r\n" + "Ani 6 5 6 5 6 5 6 5\r\n" + "Iva 4 5 4 3 4 5 2 2 4\r\n"
				+ "Ani 5.50 5.25 6.00\r\n";
		FilesUtil.writeToTextFile("input.txt", inputFromTask);
		List<String> inputList = FilesUtil.readTextFileByLines("input.txt");

		int count = Integer.parseInt(inputList.get(0));

		List<Student> students = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			Student student = new Student();
			String[] input = inputList.get(i + 1).split(" ");
			student.name = input[0];
			for (int j = 1; j < input.length; j++) {
				student.grades.add(Double.parseDouble(input[j]));
			}
			student.average = student.grades.stream().mapToDouble(a -> a).average();
			students.add(student);

		}
		ArrayList<Student> filteredStudents = new ArrayList<>();

		for (Student student : students) {
			if (student.average.getAsDouble() >= 5.00) {
				filteredStudents.add(student);
			}
		}
		filteredStudents.sort((s1, s2) -> {
			int result = s1.name.compareTo(s2.name);
			if (result == 0) {
				result = Double.compare(s2.average.getAsDouble(), s1.average.getAsDouble());
			}
			return result;
		});
		for (Student sortedStudent : filteredStudents) {
			String content = String
					.format(sortedStudent.name + " -> " + String.format("%.2f%n", sortedStudent.average.getAsDouble()));
			Files.write(Paths.get("output.txt"), content.getBytes(), StandardOpenOption.APPEND);
		}
		System.out.println("Done");
	}

}
