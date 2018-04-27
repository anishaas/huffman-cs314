import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class PriorityQueue {
	
	private ArrayList<TreeNode> queue;
	
	public ArrayList<TreeNode> getQueue() {
		return queue;
	}

	public void setQueue(ArrayList<TreeNode> queue) {
		this.queue = queue;
	}

	public static Comparator<Entry<String, Integer>> getFrequencyComparator() {
		return FREQUENCY_COMPARATOR;
	}

	final static Comparator<Entry<String, Integer>> FREQUENCY_COMPARATOR = new Comparator<Entry<String, Integer>>() { 

	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		return o1.getValue().compareTo(o2.getValue());
	} };
	
	public int getLength() {
		return this.getQueue().size();
	}
	
	public TreeNode dequeueTreeNode() {
		return this.queue.remove(0);
	}
	
	public void enqueueTreeNode(TreeNode node) {
		int value = node.getFrequency();
		TreeNode prev = queue.get(0);
		TreeNode next = null;
		if(queue.size() > 1) { 
			next = queue.get(1);
		}
		
		if(prev == null) {
			queue.add(node);
		}
		
		if(queue.size() == 1) {
			if(value < prev.getFrequency()) {
				queue.add(0, node);
				return;
			} else {
				queue.add(1, node);
				return;
			}
		}
		
		int prevFreq = prev.getFrequency();
		int nextFreq = prev.getFrequency();
		int prevPos = 0;
		int nextPos = 1;
			
		while(prevFreq <= value && nextFreq <= value && nextPos + 1 < queue.size()) {
			if(prev != null) {
				prevFreq = prev.getFrequency();
			}
			if(next != null) {
				nextFreq = next.getFrequency();
			}
			prevPos++;
			nextPos++;
			if(queue.get(prevPos) != null) {
				prev = queue.get(prevPos);
			}
			if(queue.get(nextPos) != null) {
				next = queue.get(nextPos);
			}
		}
		//next should be > value
		//shift all right elements
		int length = queue.size();
		TreeNode temp = new TreeNode();
		queue.add(temp);
		for(int j = nextPos; j < length; j++) {
			queue.set(j+1, queue.get(j));
		}
		//insert node 
		queue.set(prevPos + 1, node);
	}

	public PriorityQueue buildQueue(HashMap<String, Integer> frequencyMap) {
		ArrayList<Entry<String, Integer>> sortedFrequencies = sortCharsByFrequency(frequencyMap); 
		ArrayList<TreeNode> queueArray = new ArrayList<TreeNode>();
		String data = sortedFrequencies.get(0).getKey();
		int freq = sortedFrequencies.get(0).getValue();
		for(int i = 0; i < sortedFrequencies.size(); i++) {
			//create new node
			data = sortedFrequencies.get(i).getKey();
			freq = sortedFrequencies.get(i).getValue();
			TreeNode node = new TreeNode(data, freq);
			queueArray.add(node);
		}
		PriorityQueue queue = new PriorityQueue();
		queue.setQueue(queueArray);
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
