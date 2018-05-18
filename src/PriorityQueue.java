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

        //more than 1 node in queue
        if (queue.size() > 1) {
            next = queue.get(1);
        }

        if (queue.size() == 1) {
            if (value > queue.get(0).getFrequency()) {
                queue.add(node);
            } else {
                TreeNode temp = new TreeNode();
                queue.add(temp);
                queue.set(1, queue.get(0));
                queue.set(0, node);
            }
        } else if(queue.size() == 2) {
            TreeNode temp = new TreeNode();
            queue.add(temp);
            for(int i = 0; i < 2; i++) {
                if(value < queue.get(i).getFrequency()) {
                    //shift
                    for(int j = 1; j > i; j--) {
                        queue.set(j + 1, queue.get(j));
                    }
                    queue.set(i, node);
                }
                queue.set(2, node);
            }
        } else {

            int prevFreq = prev.getFrequency();
            int nextFreq = next.getFrequency();
            int prevPos = 0;
            int nextPos = 1;

            while (prevFreq <= value && nextFreq <= value && nextPos + 1 < queue.size()) {
                //changing position
                prevPos++;
                nextPos++;
                //grabbing nodes at updated indices
                if (queue.get(prevPos) != null) {
                    prev = queue.get(prevPos);
                }
                if (queue.get(nextPos) != null) {
                    next = queue.get(nextPos);
                }
                //updating freq
                if (prev != null) {
                    prevFreq = prev.getFrequency();
                }
                if (next != null) {
                    nextFreq = next.getFrequency();
                }

            }
            //next should be > value
            //shift all right elements
            TreeNode temp = new TreeNode();
            int length = queue.size();
            queue.add(temp);
            for (int j = length - 1; j >= nextPos; j--) {
                queue.set(j + 1, queue.get(j));
            }
            //insert node
            queue.set(prevPos + 1, node);
        }
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
