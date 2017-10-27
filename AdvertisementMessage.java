import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AdvertisementMessage {

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

		HashMap<String, String[]> phrases = new HashMap<>();
		List<String[]> phrases1 = new ArrayList<>();

		String phrasesArr = "Excellent product., Such a great product., "
				+ "I always use that product., Best product of its category., Exceptional product., "
				+ "I can’t live without this product.\r\n";

		String eventsArr = "Now I feel good., I have succeeded with this product.,	"
				+ "Makes miracles. I am happy of the results!, I cannot believe but now I feel awesome., "
				+ "Try it yourself, I am very satisfied., I feel great!\r\n";

		String authorsArr = "Diana, Petya, Stella, Elena, Katya, Iva, Annie, Eva\r\n";

		String citiesArr = "Burgas, Sofia, Plovdiv, Varna, Ruse \r\n";

		String[] holdAllArr = new String[] { phrasesArr, eventsArr, authorsArr, citiesArr };

		for (int i = 0; i < holdAllArr.length; i++) {
			String content = holdAllArr[i];
			Files.write(Paths.get("input.txt"), content.getBytes(), StandardOpenOption.APPEND);

		}

		int numOfCobination = 4;
		File f = new File("input.txt");
		
		BufferedReader b = new BufferedReader(new FileReader(f));
		String readLine = "";
		Random rand = new Random();
		ArrayList<String[]> arrayList = new ArrayList<>();
		while ((readLine = b.readLine()) != null) {
			arrayList.add(readLine.split(",\\s+"));

		}
		
		for (int i = 0; i < numOfCobination; i++) {
			int randPhrase = rand.nextInt(arrayList.get(0).length) + 0;
			int randEvent = rand.nextInt(arrayList.get(1).length) + 0;
			int randAuthor = rand.nextInt(arrayList.get(2).length) + 0;
			int randCities = rand.nextInt(arrayList.get(3).length) + 0;
			
			System.out.print(arrayList.get(0)[randPhrase]+" ");
			System.out.print(arrayList.get(1)[randEvent]+" ");
			System.out.print(arrayList.get(2)[randAuthor]+" ");
			System.out.print(arrayList.get(3)[randCities]+"\r\n");
			
			String content = arrayList.get(0)[randPhrase]+" "+arrayList.get(1)[randEvent]+" "+arrayList.get(2)[randAuthor]+" "+arrayList.get(3)[randCities]+"\r\n";
			Files.write(Paths.get("output.txt"), content.getBytes(), StandardOpenOption.APPEND);
		}
	}
}