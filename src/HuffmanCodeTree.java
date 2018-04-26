import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class HuffmanCodeTree {
	
	public static void main(String args[]) throws FileNotFoundException {
		Scanner input = new Scanner(new File("poem.txt"));
		FrequencyCalculator calc = new FrequencyCalculator();
		HashMap<String, Integer> map = calc.getFrequencyMap(input);
		PriorityQueue q = new PriorityQueue();
		PriorityQueue queue = q.buildQueue(map);
		for(TreeNode n : queue.getQueue()) {
			System.out.println(n.getValue() + ": " + n.getFrequency());
		}
		System.out.println();
		System.out.println("enqeued node: ");
		HuffmanCodeTree t = new HuffmanCodeTree();
		PriorityQueue queue1 = t.buildTree(queue);
		for(TreeNode n : queue1.getQueue()) {
			System.out.println(n.getValue() + ": " + n.getFrequency());
		}
	}
	
	//build tree from priority queue
	public PriorityQueue buildTree(PriorityQueue queue) {
		if(queue != null) {
			TreeNode n1 = queue.dequeueTreeNode();
			TreeNode n2 = queue.dequeueTreeNode();
			TreeNode parent = new TreeNode(n1, n2);
			queue.enqueueTreeNode(parent);
		}
		return queue;
	}
}
