import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

public class PriorityQueue {
	
	private ArrayList<TreeNode> queue;
	
	final static Comparator<Entry<String, Integer>> FREQUENCY_COMPARATOR = new Comparator<Entry<String, Integer>>() { 

	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		return o1.getValue().compareTo(o2.getValue());
	} };

	public ArrayList<TreeNode> buildQueue(HashMap<String, Integer> frequencyMap) {
		ArrayList<Entry<String, Integer>> sortedFrequencies = sortCharsByFrequency(frequencyMap); 
		String data = sortedFrequencies.get(0).getKey();
		int freq = sortedFrequencies.get(0).getValue();
		for(int i = 1; i < sortedFrequencies.size(); i++) {
			//create new node
			data = sortedFrequencies.get(i).getKey();
			freq = sortedFrequencies.get(i).getValue();
			TreeNode node = new TreeNode(data, freq);
			queue.add(node);
		}
		return queue;
	}
	
	private ArrayList<Entry<String, Integer>> sortCharsByFrequency(HashMap<String, Integer> frequencyMap) {
		Set vals = frequencyMap.entrySet();
		//convert set to list
		ArrayList<Entry<String, Integer>> sortedVals = new ArrayList<Entry<String, Integer>>(vals);
		//sort list
		Collections.sort(sortedVals, FREQUENCY_COMPARATOR);
		return sortedVals;
	}
}
