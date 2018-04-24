import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class PriorityQueue {
	
	static class Node {
		String data;
		int frequency;
		Node next;
		
		Node() {
			
		}
		
		Node(String data, Node next, int freq) {
			this.data = data;
			this.next = next;
			this.frequency = freq;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public int getFrequency() {
			return frequency;
		}

		public void setFrequency(int freq) {
			this.frequency = freq;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
				
	}
	
	final static Comparator<Entry<String, Integer>> FREQUENCY_COMPARATOR = new Comparator<Entry<String, Integer>>() { 

	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		return o1.getValue().compareTo(o2.getValue());
	} };

	public static Node buildQueue(HashMap<String, Integer> frequencyMap) {
		ArrayList<Entry<String, Integer>> sortedFrequencies = sortCharsByFrequency(frequencyMap); 
		LinkedList<Node> queue = new LinkedList<Node>();
		String data = sortedFrequencies.get(0).getKey();
		int freq = sortedFrequencies.get(0).getValue();
		//create head 
		Node head = new Node(data, null, freq);
		Node temp = head;
		for(int i = 1; i < sortedFrequencies.size(); i++) {
			//create new node
			data = sortedFrequencies.get(i).getKey();
			freq = sortedFrequencies.get(i).getValue();
			Node next = new Node(data, null, freq);
			temp.next = next;
			temp = temp.next;
		}
		return head;
	}
	
	private static ArrayList<Entry<String, Integer>> sortCharsByFrequency(HashMap<String, Integer> frequencyMap) {
		Set vals = frequencyMap.entrySet();
		//convert set to list
		ArrayList<Entry<String, Integer>> sortedVals = new ArrayList<Entry<String, Integer>>(vals);
		//sort list
		Collections.sort(sortedVals, FREQUENCY_COMPARATOR);
		return sortedVals;
	}
}
