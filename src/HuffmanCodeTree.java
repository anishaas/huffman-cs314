import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class HuffmanCodeTree {
	
	public static void main(String args[]) throws FileNotFoundException {
		Scanner input = new Scanner(new File("poem.txt"));
		FrequencyCalculator calc = new FrequencyCalculator();
		HashMap<String, Integer> map = calc.getFrequencyMap(input);
		PriorityQueue.Node head = PriorityQueue.buildQueue(map);
		PriorityQueue.Node temp = head;
		while(temp != null) {
			System.out.println(temp.data + ": " + temp.frequency);
			temp = temp.next;
		}
		System.out.println();
		System.out.println("Enqueued new node:");
		buildTree(head);
	}
	
	//build tree from priority queue
	public static void buildTree(PriorityQueue.Node head) {
		//create subtrees from pair of nodes
		PriorityQueue.Node temp = head;
		PriorityQueue.Node next = head.getNext();
		//while pair is present
		while(temp != null && next != null) {
			//create new node
			TreeNode left = new TreeNode(temp.getData(), temp.getFrequency());
			TreeNode right = new TreeNode(next.getData(), next.getFrequency());	
			TreeNode parent = new TreeNode(left, right);
			temp = next.getNext();
			if(temp != null) {
				next = temp.getNext();
			}
			//enqueue the new node
			enqueue(parent, temp, next);
		}
		printTree();
	}
	
	private static void printTree(TreeNode root) {
		
	}
	
	private static void enqueue(TreeNode parent, PriorityQueue.Node target, PriorityQueue.Node pointer) {
		//check if this is the last pair
		if(pointer.getNext() != null) {
			//enqueue node using target and pointer 
			//to identify correct position
			PriorityQueue.Node node = new PriorityQueue.Node();
			node.setFrequency(parent.getFrequency());
			int value = parent.getFrequency();
			while(target.getFrequency() < value && pointer.getFrequency() < value) {
				target = pointer;
				pointer = pointer.getNext(); 
				//position found case 1
				if(pointer.getFrequency() > value) {
					target.setNext(node);
					node.setNext(pointer);
				}
			}
		}
		//last pair frequencies form root
		setRoot(target, pointer);
	}
	
	private static TreeNode setRoot(TreeNode left, TreeNode right) {
		TreeNode root = new TreeNode(left, right);
		return root;		
	}
}
