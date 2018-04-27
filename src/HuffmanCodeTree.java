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
		System.out.println("tree root: ");
		HuffmanCodeTree t = new HuffmanCodeTree();
		TreeNode root = t.buildTree(queue);
		System.out.println(root.getFrequency());
	}
	
	//build tree from priority queue
	public TreeNode buildTree(PriorityQueue queue) {
		TreeNode root = null;
		if(queue != null) {
			while(queue.getLength() > 2) {
				TreeNode n1 = queue.dequeueTreeNode();
				TreeNode n2 = queue.dequeueTreeNode();
				TreeNode parent = new TreeNode(n1, n2);
				queue.enqueueTreeNode(parent);
			}
			
			//final two trees
			TreeNode left = queue.dequeueTreeNode();
			TreeNode right = queue.dequeueTreeNode();
			root = new TreeNode(left, right);
		}
		return root;
	}
}
