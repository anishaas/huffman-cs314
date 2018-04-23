import java.util.HashMap;
import java.util.Scanner;

public class FrequencyCalculator {
	
	//recieve file and tally characters, return hashmap
	public HashMap<String, Integer> getFrequencyMap(Scanner text) {
		HashMap<String, Integer> frequencyMap = new HashMap<String, Integer>();
		//translate the file into string
		while(text.hasNextLine()) {
			String line = text.nextLine();
			frequencyMap = tallyLine(line, frequencyMap);
		}
		return frequencyMap;		
	}
	
	private static HashMap<String, Integer> tallyLine(String line, HashMap<String, Integer> map) {
		@SuppressWarnings("resource")
		Scanner words = new Scanner(line);
		while(words.hasNext()) {
			String current = words.next();
			tallyWord(current, map);
		}		
		return map;
	}
	
	private static HashMap<String, Integer> tallyWord(String word, HashMap<String, Integer> map) {
		for(int i = 0; i < word.length(); i++) {
			String current = Character.toString(word.charAt(i));
			//update frequency map
			if(!map.containsKey(current)) {
				//add new key to map, initial frequency
				map.put(current, 0);
			} 
			map.put(current, map.get(current) + 1);
		}
		return map;
	}
}
